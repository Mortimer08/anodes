package controllers.ground;

import models.ground.Cell;
import play.modules.router.Get;
import play.mvc.Controller;

import java.util.List;

public class Cells extends Controller {
    @Get("/cells/list")
    public static void list(){
        List<Cell> cells = Cell.findAll();
        render(cells);
    }
}
