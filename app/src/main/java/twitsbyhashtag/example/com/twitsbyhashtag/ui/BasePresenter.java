package twitsbyhashtag.example.com.twitsbyhashtag.ui;

import android.util.Log;

import com.androidnetworking.common.ANConstants;
import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import io.reactivex.disposables.CompositeDisposable;
import twitsbyhashtag.example.com.twitsbyhashtag.AppConstants;
import twitsbyhashtag.example.com.twitsbyhashtag.R;
import twitsbyhashtag.example.com.twitsbyhashtag.data.DataManager;
import twitsbyhashtag.example.com.twitsbyhashtag.data.network.pojo.ApiError;
import twitsbyhashtag.example.com.twitsbyhashtag.utils.rx.SchedulersProvider;

/**
 * Created by Smolyaninov on 10.12.2017.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = "BasePresenter";

    private final DataManager dataManager;
    private final SchedulersProvider schedulersProvider;
    private final CompositeDisposable compositeDisposable;

    private V mvpView;

    @Inject
    public BasePresenter(DataManager dataManager,
                         SchedulersProvider schedulersProvider,
                         CompositeDisposable compositeDisposable) {
        this.dataManager = dataManager;
        this.schedulersProvider = schedulersProvider;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
        mvpView = null;

    }

    @Override
    public void handleApiError(ANError error) {

        if (error == null || error.getErrorBody() == null) {
            getMvpView().onError(R.string.error_something_wrong);
            return;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.CONNECTION_ERROR)) {
            getMvpView().onError(R.string.error_no_internet_connection);
            return;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.REQUEST_CANCELLED_ERROR)) {
            getMvpView().onError(R.string.error_retry);
            return;
        }

        final GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        try {
            ApiError apiError = gson.fromJson(error.getErrorBody(), ApiError.class);

            if (apiError == null || apiError.getMessage() == null) {
                getMvpView().onError(R.string.error_something_wrong);
                return;
            }

            switch (error.getErrorCode()) {
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                case HttpsURLConnection.HTTP_FORBIDDEN:
                    setUserAsLoggedOut();
                    getMvpView().openActivityOnTokenExpire();
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                case HttpsURLConnection.HTTP_NOT_FOUND:
                default:
                    getMvpView().onError(apiError.getMessage());
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            Log.e(TAG, "handleApiError", e);
            getMvpView().onError(R.string.error_something_wrong);
        }
    }

    @Override
    public void setUserAsLoggedOut() {
        getDataManager().setAccessToken(null);
    }

    public V getMvpView() {
        return mvpView;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public boolean isViewAttached() {
       return mvpView != null;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
