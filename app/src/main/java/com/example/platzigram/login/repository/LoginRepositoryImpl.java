package com.example.platzigram.login.repository;

import com.example.platzigram.login.presenter.LoginPresenter;

public class LoginRepositoryImpl implements LoginRepository{

    LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void singIn(String username, String password) {
        boolean success = true;
        if (true){
            presenter.loginSuccess();
        }else {
            presenter.loginError("Ocurrió un Error");
        }
    }
}
