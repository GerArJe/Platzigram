package com.example.platzigram.login.repository;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.platzigram.login.presenter.LoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginRepositoryImpl implements LoginRepository{

    LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void singIn(String username, String password, Activity activity , FirebaseAuth firebaseAuth) {

        firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            presenter.loginSuccess();
                        }else {
                            presenter.loginError("Ocurrió un Error");
                        }
                    }
                });

//        boolean success = true;
//        if (true){
//            presenter.loginSuccess();
//        }else {
//            presenter.loginError("Ocurrió un Error");
//        }
    }
}
