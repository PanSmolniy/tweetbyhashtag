package twitsbyhashtag.example.com.twitsbyhashtag.ui;

import com.androidnetworking.error.ANError;

/**
 * Created by Smolyaninov on 10.12.2017.
 */

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(ANError error);

    void setUserAsLoggedOut();

}
