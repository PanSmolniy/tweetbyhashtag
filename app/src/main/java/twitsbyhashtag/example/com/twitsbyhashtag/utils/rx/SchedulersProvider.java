package twitsbyhashtag.example.com.twitsbyhashtag.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by Smolyaninov on 10.12.2017.
 */

public interface SchedulersProvider {


    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
