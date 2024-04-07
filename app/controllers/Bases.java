package controllers;

import controllers.auth.Secure;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Bases extends Controller {

}
