package br.com.train.springkeycloack.service;

import br.com.train.springkeycloack.domain.form.UserForm;

public interface LoginService<T> {

    /**
     * Performs the login operation.
     *
     * @param userForm the user credentials
     * @return a response entity containing the login result
     */
    T doLogin(UserForm userForm);

    /**
     * Refreshes the access token using the provided refresh token.
     *
     * @param refreshToken the refresh token to be used for refreshing the access token
     * @return a response entity containing the new access token
     */
    T refreshToken(String refreshToken);

    /**
     * Performs the logout operation.
     *
     * @param refreshToken the refresh token to be used for logout
     * @return a response entity indicating the logout result
     */
    T doLogout(String refreshToken);
}
