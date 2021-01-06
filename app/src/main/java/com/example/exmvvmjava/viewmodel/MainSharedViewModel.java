package com.example.exmvvmjava.viewmodel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainSharedViewModel extends ViewModel {

    private MutableLiveData<Fragment> _replaceFragment = new MutableLiveData<>();
    public LiveData<Fragment> replaceFragment = _replaceFragment;

    private MutableLiveData<Fragment> _addFragment = new MutableLiveData<>();
    public LiveData<Fragment> addFragment = _addFragment;

    private MutableLiveData<String> _showErrorMessage = new MutableLiveData<>();
    public LiveData<String> showErrorMessage = _showErrorMessage;

    public void replaceFragment(Fragment fragment) {
        _replaceFragment.postValue(fragment);
    }

    public void addFragment(Fragment fragment) {
        _addFragment.postValue(fragment);
    }

    public void showErrorMessage(String message){
        _showErrorMessage.postValue(message);
    }
}
