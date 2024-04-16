package controllers.auth;

import common.Data;
import models.auth.User;
import play.Logger;
import play.i18n.Messages;
import play.mvc.Mailer;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

public class Mails extends Mailer {
    public static void sendActivationEmail(
            User user, String activationKey, String expirationDays
    ) {
        setCharset("UTF-8");
        setSubject(Messages.get("Mails.sendActivationEmail"));
        try {
            setFrom(new InternetAddress(Data.MAILER_FROM_ADDRESS, Data.MAILER_FROM_PERSONAL));
            Logger.info("setFrom: ");
        } catch (UnsupportedEncodingException e) {
            setFrom(Data.MAILER_FROM_ADDRESS);
        }
        addRecipient(user.email);
        setContentType("text/html");
        Logger.info("emailfrom: " + Data.MAILER_FROM_ADDRESS);
        send(user, activationKey, expirationDays);
        Logger.info("emailed to: " + user.email);

    }
}
