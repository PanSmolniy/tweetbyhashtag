package twitsbyhashtag.example.com.twitsbyhashtag;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import javax.inject.Inject;

import twitsbyhashtag.example.com.twitsbyhashtag.data.DataManager;
import twitsbyhashtag.example.com.twitsbyhashtag.di.component.ApplicationComponent;
import twitsbyhashtag.example.com.twitsbyhashtag.di.component.DaggerApplicationComponent;
import twitsbyhashtag.example.com.twitsbyhashtag.di.module.ApplicationModule;

import static com.androidnetworking.interceptors.HttpLoggingInterceptor.Level.BODY;

/**
 * Created by Smolyaninov on 10.12.2017.
 */

public class TweetsByHashtagApp extends Application {

    @Inject
    DataManager dataManager;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        applicationComponent.inject(this);

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(BODY);
        }
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
