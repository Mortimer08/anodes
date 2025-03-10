package common;

import play.Play;

import java.io.File;

public class Data {

    public static final String HOST = Play.configuration.getProperty("application.host", "http://anodes.ru");
    public static final String MAILER_FROM_ADDRESS = Play.configuration.getProperty("mail.smtp.user", "mortimer08@gmail.com)");
    public static final String MAILER_FROM_PERSONAL = Play.configuration.getProperty("mail.smtp.personal","Anodes Admin");
    public static final String DAYS_FOR_ACTIVATION = "3";
    public static final String ADMIN_EMAIL = Play.configuration.getProperty("mail", "mortimer08@gmil.com");
    public static final File DATA = Play.getFile("data");
    public static final File DOWNLOAD = new File(DATA, "download");
}
