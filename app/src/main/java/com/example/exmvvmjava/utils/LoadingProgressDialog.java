package com.example.exmvvmjava.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class LoadingProgressDialog {

    private static LoadingProgressDialog INSTANCE;

    private static ProgressDialog progressDialog;

    private static Context mContext;

    public LoadingProgressDialog(){}

    public static LoadingProgressDialog getInstance(Context context){
        mContext = context;
        if(INSTANCE == null) INSTANCE = new LoadingProgressDialog();

        return INSTANCE;
    }

    public void start(String title, String message){
        if(progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Large);
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    public void end(){
        if(progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
