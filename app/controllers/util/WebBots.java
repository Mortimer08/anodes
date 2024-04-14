package controllers.util;

import play.modules.router.Get;
import play.mvc.Controller;

public class WebBots extends Controller {
    @Get("/robots.txt")
    public static void robots(){
        render("util/robots.txt");
    }
}
