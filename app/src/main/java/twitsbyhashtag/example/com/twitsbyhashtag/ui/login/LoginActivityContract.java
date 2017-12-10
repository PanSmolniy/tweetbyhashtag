package twitsbyhashtag.example.com.twitsbyhashtag.ui.login;

import twitsbyhashtag.example.com.twitsbyhashtag.ui.MvpPresenter;
import twitsbyhashtag.example.com.twitsbyhashtag.ui.MvpView;

/**
 * Created by Smolyaninov on 10.12.2017.
 */

public interface LoginActivityContract {

    interface View extends MvpView {
        void showEmailError();
        void showPasswordError();
    }

    interface Presenter<V extends View> extends MvpPresenter<V>{
        void processLoginClick(String email, String password);
        void openMainActivity();
    }

}
