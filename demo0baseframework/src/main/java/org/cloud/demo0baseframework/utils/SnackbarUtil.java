package org.cloud.demo0baseframework.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * @author d05660ddw
 * @version 1.0 2017/7/8
 */

public class SnackbarUtil {

    public static void show(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

}
