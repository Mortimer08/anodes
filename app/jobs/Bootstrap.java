package jobs;

import models.auth.User;
import play.Logger;
import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob() {
        Logger.info("bootstrap");
        createSuperuser();
    }

    private static void createSuperuser() {
        final String email = Play.configuration.getProperty("email", "mortimer08@gmail.com");
        if (User.findByEmail(email) == null) {
            User.createSuperuser(
                    Play.configuration.getProperty("email", "mortimer08@gmail.com"),
                    Play.configuration.getProperty("password", "123qweasd")
            );
        }
    }

}
