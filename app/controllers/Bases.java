package controllers;

import controllers.auth.Secure;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Bases extends Controller {


    protected static void updateFilter(final Object o) {
        Cache.set(request.controller + session.getId(), o);
    }

    protected static <T> T getNewFilter(final Class<T> clazz) {
        T filter = Cache.get(request.controller + session.getId(), clazz);
        if (filter == null) {
            try {
                filter = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return filter;
    }
}
