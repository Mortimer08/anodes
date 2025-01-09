package jobs;

import models.auth.User;
import models.ground.Cell;
import models.ground.Row;
import models.ground.Take;
import models.ground.Tier;
import play.Logger;
import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob() {
        Logger.info("bootstrap");
        createSuperuser();
        createGroundData();
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

    private static void createGroundData() {
        if (Tier.count() == 0 && Row.count() == 0 && Cell.count() == 0 && Take.count() == 0) {
            Logger.info("create");
//            Ground.create();
        }
    }

}
