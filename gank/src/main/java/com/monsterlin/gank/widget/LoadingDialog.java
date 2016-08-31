package com.monsterlin.gank.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.monsterlin.gank.R;


/**
 * Created by monsterLin on 7/3/2016.
 */
public class LoadingDialog {

    private AlertDialog alertDialog;
    private Context mContext;

    public LoadingDialog(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_alert, null);
        alertDialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();
    }

    public void showDialog() {
        alertDialog.show();
    }


    public void dismissDialog() {
        alertDialog.dismiss();
    }
}
