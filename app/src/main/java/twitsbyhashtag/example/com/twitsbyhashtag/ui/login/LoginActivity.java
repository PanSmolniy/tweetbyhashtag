package twitsbyhashtag.example.com.twitsbyhashtag.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import twitsbyhashtag.example.com.twitsbyhashtag.R;
import twitsbyhashtag.example.com.twitsbyhashtag.ui.BaseActivity;

public class LoginActivity
        extends BaseActivity
        implements LoginActivityContract.View {

    @BindView(R.id.login_button)
    Button button;

    @BindView(R.id.email)
    EditText emailInput;

    @BindView(R.id.password)
    EditText pwInput;

    @Inject
    LoginActivityContract.Presenter<LoginActivityContract.View> presenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.onAttach(LoginActivity.this);
    }

    @OnClick(R.id.login_button)
    public void onLoginClick(View v) {
        presenter.processLoginClick(emailInput.getText().toString(), pwInput.getText().toString());
    }

    @Override
    public void showEmailError() {
        emailInput.setError(getString(R.string.error_email));
    }

    @Override
    public void showPasswordError() {
        pwInput.setError(getString(R.string.error_password));
    }
}
