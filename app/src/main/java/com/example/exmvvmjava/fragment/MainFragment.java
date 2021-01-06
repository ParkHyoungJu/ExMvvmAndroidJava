package com.example.exmvvmjava.fragment;

import com.example.exmvvmjava.BaseFragment;
import com.example.exmvvmjava.databinding.FragmentMainBinding;

public class MainFragment extends BaseFragment<FragmentMainBinding> {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected FragmentMainBinding createDataBinding() {
        return FragmentMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initViews() {
        super.initViews();
    }
}