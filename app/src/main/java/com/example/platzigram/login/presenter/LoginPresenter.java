package com.example.platzigram.login.presenter;

import android.app.Activity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public interface LoginPresenter {
    void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth); //Interactor
    void loginSuccess();
    void loginError(String error);

}
