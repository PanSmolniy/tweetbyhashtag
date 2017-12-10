package twitsbyhashtag.example.com.twitsbyhashtag.ui.login;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import twitsbyhashtag.example.com.twitsbyhashtag.data.DataManager;
import twitsbyhashtag.example.com.twitsbyhashtag.ui.BasePresenter;
import twitsbyhashtag.example.com.twitsbyhashtag.utils.rx.SchedulersProvider;

/**
 * Created by Smolyaninov on 10.12.2017.
 */

public class LoginActivityPresenter<V extends LoginActivityContract.View> extends BasePresenter<V>
        implements LoginActivityContract.Presenter<V> {


    @Inject
    public LoginActivityPresenter(DataManager dataManager,
                                  SchedulersProvider schedulersProvider,
                                  CompositeDisposable compositeDisposable) {
        super(dataManager, schedulersProvider, compositeDisposable);
    }

    @Override
    public void processLoginClick(String email, String password) {
        if (email == null || email.isEmpty()) {

        }
    }

    @Override
    public void openMainActivity() {

    }
}
