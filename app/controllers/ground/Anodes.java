package controllers.ground;

import controllers.Bases;
import models.ground.Anode;
import play.modules.router.Get;

import java.util.List;

public class Anodes extends Bases {
@Get("/anodes/list")
    public static void list(){
    List<Anode> anodes = Anode.findAll();
    render(anodes);
}
}
