package common;

import play.Play;

public class Data {
    public static final String HOST = Play.configuration.getProperty("application.host", "anodes.ru");
    public static final String MAILER_FROM_ADDRESS = Play.configuration.getProperty("mail.smtp.user", "mortimer08@gmail.com)");
    public static final String MAILER_FROM_PERSONAL = Play.configuration.getProperty("mail.smtp.personal","Anodes Admin");
    public static final String DAYS_FOR_ACTIVATION = "3";
}
