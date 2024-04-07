package common;

import play.Play;

public class Data {
    public static final String HOST = Play.configuration.getProperty("application.host", "localhost");
}
