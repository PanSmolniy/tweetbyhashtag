package twitsbyhashtag.example.com.twitsbyhashtag.ui;

import android.content.Context;

/**
 * Created by Smolyaninov on 10.12.2017.
 */

public interface MvpView {

    void showLoading();
    void stopLoading();
    void onError(String message);
    void onError(int resId);
    void openActivityOnTokenExpire();
    boolean isNetworkConnected();
    void hideKeyboard();

}
