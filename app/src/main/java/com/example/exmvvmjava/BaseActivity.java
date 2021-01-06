package com.example.exmvvmjava;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import com.example.exmvvmjava.activity.MainActivity;
import com.example.exmvvmjava.utils.LoadingProgressDialog;

public abstract class BaseActivity<DATA_BINDING extends ViewDataBinding> extends AppCompatActivity {

    protected DATA_BINDING binding;

    private AlertDialog dialog;

    protected abstract DATA_BINDING createDataBinding();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();

        setExceptionThrow();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void initViews() {
        binding = createDataBinding();
        setContentView(binding.getRoot());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        View focusView = getCurrentFocus();
        if (focusView != null && event.getDeviceId() == 1) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
        }

        return super.dispatchKeyEvent(event);
    }

    protected void showMessage(String message, DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.title_alert);
        builder.setMessage(message);
        builder.setPositiveButton(getString(R.string.btn_confirm),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if(onClickListener != null) onClickListener.onClick(dialog, which);
                    }
                });

        LoadingProgressDialog.getInstance(this).end();

        if(dialog == null) dialog = builder.create();
        if(!dialog.isShowing()) dialog.show();

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                BaseActivity.this.dialog = null;
            }
        });
    }

    protected void showConfirmMessage(String message, DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.title_alert);
        builder.setMessage(message);
        builder.setPositiveButton(getString(R.string.btn_yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(onClickListener != null) onClickListener.onClick(dialog, which);
                        dialog.dismiss();
                    }
                });

        builder.setNegativeButton(getString(R.string.btn_no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        LoadingProgressDialog.getInstance(this).end();

        if(dialog == null) dialog = builder.create();
        if(!dialog.isShowing()) dialog.show();

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                BaseActivity.this.dialog = null;
            }
        });
    }

    // TODO: 앱 실행중 Exception 발생하면 앱 다시 실행되도록함
    private void setExceptionThrow(){
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
                Intent mStartActivity = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent mPendingIntent = PendingIntent.getActivity(getApplicationContext(), 99999, mStartActivity,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 10, mPendingIntent);
                System.exit(0);
            }
        });
    }
}
