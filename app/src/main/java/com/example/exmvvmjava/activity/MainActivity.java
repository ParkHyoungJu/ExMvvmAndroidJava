package com.example.exmvvmjava.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.exmvvmjava.BaseActivity;
import com.example.exmvvmjava.R;
import com.example.exmvvmjava.databinding.ActivityMainBinding;
import com.example.exmvvmjava.fragment.MainFragment;
import com.example.exmvvmjava.viewmodel.MainSharedViewModel;
import com.example.exmvvmjava.viewmodel.MainViewModelFactory;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private MainViewModelFactory viewModelFactory;
    private MainSharedViewModel mainSharedViewModel;

    public static void start(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(i);
    }

    @Override
    protected ActivityMainBinding createDataBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            replaceFragment(MainFragment.newInstance());
        }

        initViewModel();
        observeSharedViewModel();
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    private void initViewModel() {
        viewModelFactory = new MainViewModelFactory();
        mainSharedViewModel = new ViewModelProvider(this, viewModelFactory).get(MainSharedViewModel.class);
    }

    private void observeSharedViewModel() {
        mainSharedViewModel.replaceFragment.observe(this, new Observer<Fragment>() {
            @Override
            public void onChanged(@Nullable Fragment fragment) {
                replaceFragment(fragment);
            }
        });

        mainSharedViewModel.addFragment.observe(this, new Observer<Fragment>() {
            @Override
            public void onChanged(@Nullable Fragment fragment) {
                addFragment(fragment);
            }
        });

        mainSharedViewModel.showErrorMessage.observe(this, new Observer<String>() {

            @Override
            public void onChanged(String mString) {
                showMessage(mString, null);
            }
        });
    }
}