package controllers.information;

import controllers.Bases;
import play.modules.router.Get;

public class Photos extends Bases {

    @Get("/photo")
    public static void list() {
        render();
    }
}
