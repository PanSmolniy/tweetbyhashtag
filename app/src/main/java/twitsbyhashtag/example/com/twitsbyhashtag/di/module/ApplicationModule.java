package twitsbyhashtag.example.com.twitsbyhashtag.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import twitsbyhashtag.example.com.twitsbyhashtag.data.AppDataManager;
import twitsbyhashtag.example.com.twitsbyhashtag.data.DataManager;
import twitsbyhashtag.example.com.twitsbyhashtag.di.ApplicationContext;

/**
 * Created by Smolyaninov on 10.12.2017.
 */

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

}
