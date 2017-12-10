package twitsbyhashtag.example.com.twitsbyhashtag.data;

import android.content.Context;

import javax.inject.Inject;

import twitsbyhashtag.example.com.twitsbyhashtag.di.ApplicationContext;

/**
 * Created by Smolyaninov on 10.12.2017.
 */

public class AppDataManager implements DataManager {

    private final Context context;

    @Inject
    public AppDataManager (@ApplicationContext Context context) {
        this.context = context;
    }




    @Override
    public String getAccessToken() {
        return null;
    }

    @Override
    public void setAccessToken(String accessToken) {

    }
}
