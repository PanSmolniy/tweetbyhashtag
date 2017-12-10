package twitsbyhashtag.example.com.twitsbyhashtag.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import twitsbyhashtag.example.com.twitsbyhashtag.TweetsByHashtagApp;
import twitsbyhashtag.example.com.twitsbyhashtag.data.DataManager;
import twitsbyhashtag.example.com.twitsbyhashtag.di.ApplicationContext;
import twitsbyhashtag.example.com.twitsbyhashtag.di.module.ApplicationModule;

/**
 * Created by Smolyaninov on 10.12.2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(TweetsByHashtagApp app);

    @ApplicationContext
    Context context();

    Application application();
    DataManager getDataManager();

}
