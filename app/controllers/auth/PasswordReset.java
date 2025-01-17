package controllers.auth;

import common.utils.Signing;
import models.auth.User;
import org.apache.commons.lang.StringUtils;
import play.Logger;
import play.data.validation.*;
import play.modules.router.Get;
import play.modules.router.Post;
import play.mvc.Controller;

public class PasswordReset extends Controller {

    @Get("/password/change")
    public static void passwordChange() {
        render();
    }

    @Post("/password/change/done")
    public static void _passwordChange(final String email) {
        if (StringUtils.isNotBlank(email)) {
            final User user = User.findByEmail(email);
            if (user == null) {
                passwordResetFailure();
            }
            sendPasswordReset(user);
        }
        render();
    }

    @Get("/password/change/{activationKey}")
    public static void passwordReset(final String activationKey) {
        notFoundIfNull(activationKey);
        render(activationKey);
    }

    @Post("/password/change/{activationKey}")
    public static void _passwordReset(
            @Required final String activationKey,
            @Required @MinSize(8) @MaxSize(250) final String password,
            @Required @MinSize(8) @MaxSize(250) @Equals(value = "password", message = "PasswordReset.newPassword.notEqual") final String repeat
    ) {
        if (Validation.hasErrors()) {
            flash.error("hasErrors");
            params.flash();
            Validation.keep();
            passwordReset(activationKey);
        }
        final String email = Signing.load(activationKey);
        if (email == null) {
            passwordResetFailure();
        }
        final User user = User.findByEmail(email);
        if (user != null) {
            user.createPassword(password);
            user.save();
        }
        Logger.info("before success");
        passwordChangeComplete();
    }

    @Get("/password/change/complete")
    public static void passwordChangeComplete() {
        Logger.info("success");
        render();
    }

    @Get("/password/change/failure")
    public static void passwordResetFailure() {
        render();
    }

    private static void sendPasswordReset(final User user) {
        if (user != null) {
            final String activationKey = Signing.dump(user.email);
            final String activationDays = "3";
            Mails.sendPasswordReset(user, activationKey, activationDays);
            Mails.reportMailing(user,"Password reset");
        }
    }
}

