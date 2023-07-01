package COM2006.project.controllers;

import java.sql.*;
import COM2006.project.tables.Account;
import COM2006.project.tables.Guest;
import COM2006.project.tables.Hosts;
import COM2006.project.PasswordManager;
import COM2006.project.tables.Validation;

public class LoginController {
    private PasswordManager passwordManager;
    private Validation validation;

    public LoginController() {
        passwordManager = new PasswordManager();
        validation = new Validation();

    }

    public Boolean ComparePasswords (Account user, String enteredPassword) {
        if (user != null) {
            String storedPassword = user.getPassword();
            String storedSalt = user.getSalt();
            String hashPassword = passwordManager.hashPassword(enteredPassword, storedSalt);

            if (hashPassword == storedPassword) {
                return true;
            }
            else {
                return false;
            }

        }


        //default case
        return false;
    }

}
