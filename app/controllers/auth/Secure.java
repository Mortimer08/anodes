package controllers.auth;

import common.Data;
import models.auth.User;
import org.apache.commons.lang.StringUtils;
import play.Logger;
import play.data.validation.Validation;
import play.libs.Crypto;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Util;

public class Secure extends Controller {
    @Before(unless = {"login", "logout", "auth", "denied"})
    static void checkAccess() {
        final User user = getUser();
        if (user == null) {
            login();
        }
    }

    public static void login() {
        recallMe("Recall me");
        render();
    }

    public static void logout() {
        session.clear();
        response.removeCookie("me");
        login();
    }

    public static void auth(String email, String password) {
        final User user = User.connect(email, password);
        if (user == null) {
            Validation.addError("email", "Secure.login.error");
            Validation.keep();
            flash.keep("url");
            login();
        }
        if (!user.active) {
            Registration.sendActivationEmail(user);
            Registration.registerComplete();
        }
        isDenied("Пользователь заблокирован", user);
        rememberMe("Запомнить пользователя", user);
        openSession("Открыть сессию", user);
        isValid("Обязательные поля заполнены", user);
        redirectToOriginalURL();
    }

    @Util
    public static User getUser() {
        final String userName = connected();
        if (StringUtils.isBlank(userName)) return null;
        return getUser(userName);
    }

    @Util
    public static User getUser(final String userName) {
        final User user = User.findByName(userName);
        if (user == null || !user.active) return null;
        renderArgs.put("user", user);
        return user;
    }

    @Util
    public static String connected() {
        return session.get("username");
    }

    public static void denied() {
        render();
    }

    static void recallMe(final String s) {
        Logger.debug(s);
        Http.Cookie remember = request.cookies.get("me");
        if (remember != null && remember.value.indexOf("-") > 0) {
            String sign = remember.value.substring(0, remember.value.indexOf("-"));
            String userName = remember.value.substring(remember.value.indexOf("-") + 1);
            if (Crypto.sign(userName).equals(sign)) {
                session.put("username", userName);
                redirectToOriginalURL();
            }
        }
    }

    static void redirectToOriginalURL() {
        final String url = flash.get("url");
        redirect(url == null ? Data.HOST : url);
    }

    @Util
    public static boolean isConnected() {
        return session.contains("username");
    }

    private static void isDenied(String s, User user) {
        Logger.debug(s);
        if (user.denied) denied();
    }

    private static void isValid(String s, User user) {
        Logger.debug(s);
        if (!user.valid) {
            // TODO: 11.04.2024 make Profiles
//            Profiles.show();
        }
    }

    private static void openSession(String s, User user) {
        Logger.debug(s);
        session.put("username", user.username);
    }

    static void rememberMe(final String s, final User user) {
        Logger.debug(s);
        response.setCookie("me", Crypto.sign(user.username) + "-" + user.username, "30d");
    }
}
