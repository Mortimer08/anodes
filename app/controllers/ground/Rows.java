package controllers.ground;

import models.ground.Row;
import play.modules.router.Get;
import play.mvc.Controller;

import java.util.List;

public class Rows extends Controller {
    @Get("/rows/list")
    public static void list(){
        List<Row> rows = Row.findAll();
        render(rows);
    }
}
