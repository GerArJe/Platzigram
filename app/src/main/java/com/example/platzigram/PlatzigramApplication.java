package com.example.platzigram;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.platzigram.login.view.LoginActivity;
import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
//import com.google.firebase.crash.FirebaseCrash;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;

/**
 * Created by anahisalgado on 06/06/17.
 */

public class PlatzigramApplication extends Application {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseStorage firebaseStorage;
    private String TAG = "PlatzigramApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Crashlytics.log("Inicializando variables PlatzigramApplication");

        FacebookSdk.sdkInitialize(getApplicationContext());

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Crashlytics.log(Log.WARN, TAG, "Usuario logeado " + firebaseUser.getEmail());
                }else {
                    Crashlytics.log(Log.WARN, TAG, "Usuario No logeado ");
                }
            }
        };
        firebaseStorage = FirebaseStorage.getInstance();
    }


    public StorageReference getStorageReference(){
        return firebaseStorage.getReference();
    }
}