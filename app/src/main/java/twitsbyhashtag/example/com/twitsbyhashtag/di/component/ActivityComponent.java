package twitsbyhashtag.example.com.twitsbyhashtag.di.component;

import dagger.Component;
import twitsbyhashtag.example.com.twitsbyhashtag.di.PerActivity;
import twitsbyhashtag.example.com.twitsbyhashtag.di.module.ActivityModule;
import twitsbyhashtag.example.com.twitsbyhashtag.ui.login.LoginActivity;

/**
 * Created by Smolyaninov on 10.12.2017.
 */



@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(LoginActivity activity);
}
