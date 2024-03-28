package controllers.ground;

import models.ground.Anode;
import models.ground.Take;
import play.modules.router.Get;
import play.mvc.Controller;

import java.util.List;

public class Takes extends Controller {
    @Get("/takes/list")
    public static void list(){
        List<Take> takes = Take.findAll();
        render(takes);
    }
}
