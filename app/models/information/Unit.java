package models.information;

import java.util.List;

public class Unit {

    public CellDetail cellDetail;
    public List<TakeDetail> takeDetails;

    public Unit(CellDetail cellDetail, List<TakeDetail> takeDetails) {
        this.cellDetail = cellDetail;
        this.takeDetails = takeDetails;
    }

}
