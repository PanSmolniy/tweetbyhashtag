package twitsbyhashtag.example.com.twitsbyhashtag.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import twitsbyhashtag.example.com.twitsbyhashtag.di.ActivityContext;
import twitsbyhashtag.example.com.twitsbyhashtag.di.PerActivity;
import twitsbyhashtag.example.com.twitsbyhashtag.ui.login.LoginActivityContract;
import twitsbyhashtag.example.com.twitsbyhashtag.ui.login.LoginActivityPresenter;
import twitsbyhashtag.example.com.twitsbyhashtag.utils.rx.AppSchedulerProvider;
import twitsbyhashtag.example.com.twitsbyhashtag.utils.rx.SchedulersProvider;

/**
 * Created by Smolyaninov on 10.12.2017.
 */

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulersProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }



    @Provides
    @PerActivity
    LoginActivityContract.Presenter<LoginActivityContract.View> provideLoginPresenter(
            LoginActivityPresenter<LoginActivityContract.View> presenter) {
        return presenter;
    }

}
