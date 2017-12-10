package twitsbyhashtag.example.com.twitsbyhashtag.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import butterknife.Unbinder;
import twitsbyhashtag.example.com.twitsbyhashtag.R;
import twitsbyhashtag.example.com.twitsbyhashtag.TweetsByHashtagApp;
import twitsbyhashtag.example.com.twitsbyhashtag.di.component.ActivityComponent;

import twitsbyhashtag.example.com.twitsbyhashtag.di.component.DaggerActivityComponent;
import twitsbyhashtag.example.com.twitsbyhashtag.di.module.ActivityModule;
import twitsbyhashtag.example.com.twitsbyhashtag.ui.login.LoginActivity;
import twitsbyhashtag.example.com.twitsbyhashtag.utils.InterfaceUtils;
import twitsbyhashtag.example.com.twitsbyhashtag.utils.NetworkUtils;

/**
 * Created by Smolyaninov on 10.12.2017.
 */

public class BaseActivity
        extends AppCompatActivity
        implements MvpView {

    private ProgressDialog progressDialog;

    private ActivityComponent activityComponent;

    private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((TweetsByHashtagApp) getApplication()).getComponent())
                .build();

    }

    @Override
    public void showLoading() {
        stopLoading();
        progressDialog = InterfaceUtils.showLoadingDialog(this);
    }

    @Override
    public void stopLoading() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }

    @Override
    public void onError(String message) {
        if (message != null && !message.isEmpty()) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.error_something_wrong));
        }
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void openActivityOnTokenExpire() {
        startActivity(LoginActivity.getStartIntent(this));
        finish();
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(this);
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void setUnBinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        snackbar.show();
    }
}
