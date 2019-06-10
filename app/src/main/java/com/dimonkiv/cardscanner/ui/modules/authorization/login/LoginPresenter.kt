package com.dimonkiv.cardscanner.ui.modules.authorization.login

class LoginPresenter(private val fragment: LoginFragment): LoginContract.Presenter {
    private var login = ""
    private var password = ""

    private lateinit var view: LoginView

    init {

    }

    override fun setView(view: LoginView) {
        this.view = view
    }

    override fun onLoginTextChanged(login: String) {
        this.login = login
    }

    override fun onPasswordTextChanged(password: String) {
        this.password = password
    }

    override fun onLoginButtonClick() {
        /*if(login.isEmpty()) {
            view.showToast("Введіть логін!")
            return
        }

        if(isLoginValid()) {
            view.showToast("Некоректний логін!")
            return
        }

        if(login.isEmpty()) {
            view.showToast("Введіть пароль!")
            return
        }

        if(isPasswordValid()) {
            view.showToast("Пароль має мати не менше 6 символів!")
            return
        }*/

        fragment.authFinished()
    }

    override fun onForgotPasswordButtonClick() {

    }

    override fun onRegisterButtonClick() {
        fragment.showRegistrationFragment()
    }

    private fun isLoginValid(): Boolean {
        return !android.util.Patterns.EMAIL_ADDRESS.matcher(login).matches()
    }

    private fun isPasswordValid(): Boolean {
        if (login.length < 6)
            return true

        return false
    }
}