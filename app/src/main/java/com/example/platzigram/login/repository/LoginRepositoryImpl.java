package com.example.platzigram.login.repository;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.platzigram.login.presenter.LoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRepositoryImpl implements LoginRepository{

    LoginPresenter presenter;
    private String TAG = "LoginRepositoryImpl";

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void singIn(String username, String password, final Activity activity , FirebaseAuth firebaseAuth) {

        firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            FirebaseUser user = task.getResult().getUser();

                            SharedPreferences preferences = activity.getSharedPreferences("USER", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("email", user.getEmail());
                            editor.commit();

                            Crashlytics.log(Log.WARN, TAG, "Usuario logueado " + user.getEmail());
                            presenter.loginSuccess();
                        }else {
                            Crashlytics.log(Log.ERROR, TAG, "Ocurrió un Error");
                            presenter.loginError("Ocurrió un Error");
                        }
                    }
                });
    }
}
