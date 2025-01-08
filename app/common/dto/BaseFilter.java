package common.dto;

import java.io.Serializable;

public abstract class BaseFilter implements Serializable {

    public String sort;
    public String order;

    public void updateOrder() {
        this.order = this.order == null ? "desc" : (this.order.equals("asc") ? "desc" : "asc");
    }
}
