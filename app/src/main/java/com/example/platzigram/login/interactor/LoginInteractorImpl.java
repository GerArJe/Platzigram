package com.example.platzigram.login.interactor;

import com.example.platzigram.login.presenter.LoginPresenter;
import com.example.platzigram.login.repository.LoginRepository;
import com.example.platzigram.login.repository.LoginRepositoryImpl;

public class LoginInteractorImpl implements LoginInteractor {

    private LoginPresenter presenter;
    private LoginRepository repository;

    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
        repository = new LoginRepositoryImpl(presenter);
    }

    @Override
    public void singIn(String username, String password) {
        repository.singIn(username, password);
    }
}
