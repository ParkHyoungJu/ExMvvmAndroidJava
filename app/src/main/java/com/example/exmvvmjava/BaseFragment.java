package com.example.exmvvmjava;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.exmvvmjava.viewmodel.MainSharedViewModel;
import com.example.exmvvmjava.viewmodel.MainViewModelFactory;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public abstract class BaseFragment<DATA_BINDING extends ViewDataBinding> extends Fragment {

    protected DATA_BINDING binding;

    private MainViewModelFactory viewModelFactory;
    protected MainSharedViewModel mainSharedViewModel;

    protected int cameraBarcodeTargetId = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initViews();
        return binding.getRoot();
    }

    protected  void initViews() {
        binding = createDataBinding();
        initViewModel();
    }

    protected abstract DATA_BINDING createDataBinding();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initViewModel() {
        viewModelFactory = new MainViewModelFactory();
        mainSharedViewModel = new ViewModelProvider(requireActivity().getViewModelStore(), viewModelFactory).get(MainSharedViewModel.class);
    }

    // 세자리수 콤마찍기
    protected String commaString(String number){
        NumberFormat formatter = new DecimalFormat("#,###");
        if(number != null) {
            return formatter.format(Integer.parseInt(number));
        }

        return "";
    }

    // edittext 대문자로 나오도록
    protected void setCapsLock(EditText... editTexts){
        for(EditText edittext:editTexts){
            edittext.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        }
    }
}