package client.views.register;

import client.clientmodel.loginRegisterModel.LoginRegisterModel;

/**
 * Class used for pushing the request from controller class and sending back the responses.
 *
 * @author Dorin, Piotr
 */

public class RegisterViewModel {

    private LoginRegisterModel loginRegisterModel;

    public RegisterViewModel(LoginRegisterModel loginRegisterModel)
    {
        this.loginRegisterModel = loginRegisterModel;
    }

    public String validateRegister(String username, String email, String password, String dob) {
        return loginRegisterModel.validateRegister(username, email, password, dob);
    }

    public void clear() {
    }
}
