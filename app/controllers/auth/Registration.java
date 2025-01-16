package controllers.auth;

import common.Data;
import common.utils.Signing;
import models.auth.User;
import play.data.validation.*;
import play.mvc.Controller;
import play.mvc.Util;

public class Registration extends Controller {

    public static void signup() {
        if (Secure.isConnected()) {
            session.clear();
            response.removeCookie("me");
        }
        render();
    }

    public static void register(
            @Required @Email @MaxSize(255) final String regEmail,
            @Required @MinSize(6) @MaxSize(255) final String regPassword
    ) {
        if (Validation.hasErrors()) {
            Validation.addError("register", "secure.error");
            Validation.keep();
            Secure.login();
        }
        checkEmail(regEmail);
        createInactiveUser(regEmail, regPassword);
        registerComplete();
    }

    public static void registerComplete() {
        render();
    }

    private static void checkEmail(String email) {
        if (User.find("email = ?1", email).first() != null) {
            Validation.addError("email", "Registration.signup.emailExists");
            Validation.keep();
            Secure.login();
        }
    }

    private static void createInactiveUser(String email, String password) {
        final User newUser = new User(email, password);
        newUser.create();
        sendActivationEmail(newUser);
    }

    @Util
    public static void sendActivationEmail(final User user) {
        final String activationKey = Signing.dump(user.email);
        final String expirationDays = Data.DAYS_FOR_ACTIVATION;
        Mails.sendActivationEmail(user, activationKey, expirationDays);
        Mails.reportMailing(user);
    }

    public static void activate(final String activationKey) {
        final String email = Signing.load(activationKey);
        if (email != null) {
            final User user = User.findByEmail(email);
            if (user != null) {
                user.active = true;
                user.save();
                activationComplete();
            }
        }
        activationFailure();
    }

    public static void activationComplete() {
        render();
    }

    public static void activationFailure() {
        render();
    }
}
