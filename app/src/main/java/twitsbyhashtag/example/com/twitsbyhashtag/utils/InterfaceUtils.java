package twitsbyhashtag.example.com.twitsbyhashtag.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.androidnetworking.model.Progress;

import twitsbyhashtag.example.com.twitsbyhashtag.R;

/**
 * Created by Smolyaninov on 10.12.2017.
 */

public class InterfaceUtils {

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

}
