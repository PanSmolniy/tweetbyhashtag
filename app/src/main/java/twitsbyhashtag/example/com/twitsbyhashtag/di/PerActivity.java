package twitsbyhashtag.example.com.twitsbyhashtag.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Smolyaninov on 10.12.2017.
 */

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface PerActivity {
}
