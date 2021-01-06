package com.example.exmvvmjava.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainSharedViewModel.class)) {
            return (T) new MainSharedViewModel();
        } else {
            throw new IllegalArgumentException("Unknown ViewModelClass");
        }
    }
}