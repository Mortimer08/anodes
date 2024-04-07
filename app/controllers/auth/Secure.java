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
    @Before(unless = {"login"})
    static void checkAccess() {
        final User user = getUser();
        if (user == null) login();
    }

    public static void login() {
        recallMe("Recall me");
        render();
    }

    public static void auth(String email, String password) {
        final User user = User.connect(email, password);
        if (user == null) {
            // TODO: 05.04.2024 Add validation
            /*Validation.addError("email","Secure.login.error");
            Validation.keep();
            flash.keep("url");*/
            login();
        }
        redirectToOriginalURL();
    }

    @Util
    public static User getUser() {
        final String userName = connected();
        if (StringUtils.isNotBlank(userName)) return null;
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
}
