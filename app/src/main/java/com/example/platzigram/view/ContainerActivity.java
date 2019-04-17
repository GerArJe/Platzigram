package com.example.platzigram.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.platzigram.R;
import com.example.platzigram.login.view.CreateAccountActivity;
import com.example.platzigram.login.view.LoginActivity;
import com.example.platzigram.post.view.HomeFragment;
import com.example.platzigram.view.fragment.ProfileFragment;
import com.example.platzigram.view.fragment.SearchFragment;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ContainerActivity extends AppCompatActivity {

    private static final String TAG = "ContainerActivity";
    final HomeFragment homeFragment = new HomeFragment();
    final ProfileFragment profileFragment  = new ProfileFragment();
    final SearchFragment searchFragment = new SearchFragment();

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.log("Iniciando " + TAG);
        setContentView(R.layout.activity_container);

        firebaseInitialize();

        //set HomeFragment as Default on first load (Login)
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, homeFragment)
                    .commit();
        }

        BottomNavigationView bottombar = findViewById(R.id.bottombar);
        bottombar.setSelectedItemId(R.id.home);

        bottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.home:
                                addFragment(homeFragment);
                                break;
                            case R.id.profile:
                                addFragment(profileFragment);
                                break;
                            case R.id.search:
                                addFragment(searchFragment);
                                break;
                        }
                        return true;
                    }

                    //Set fragment
                    private void addFragment(Fragment fragment) {
                        if (null != fragment) {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.container, fragment)
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
        });

    }

    private void firebaseInitialize(){
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Log.w(TAG, "Usuario logueado " + firebaseUser.getEmail());
                }else {
                    Log.w(TAG, "Usuario no logueado ");
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mSignOut:
                firebaseAuth.signOut();

                if (LoginManager.getInstance() != null){
                    LoginManager.getInstance().logOut();
                }
                Toast.makeText(this, "Se cerro la sesión", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(ContainerActivity.this, LoginActivity.class);
                startActivity(intent);
                break;

            case  R.id.mAbout:
                Toast.makeText(this, "Platzigram by Platzi", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
