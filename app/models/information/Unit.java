package models.information;

import java.util.List;

public class Unit {

    public CellDetail cellDetail;
    public List<TakeDetail> takeDetails;

    public Unit(CellDetail cellDetail, List<TakeDetail> takeDetails) {
        this.cellDetail = cellDetail;
        this.takeDetails = takeDetails;
    }

    public TakeDetail takeDetailById(Long id) {
        for (TakeDetail takeDetail : takeDetails) {
            if (takeDetail.take.id.equals(id)) {
                return takeDetail;
            }
        }
        return null;
    }

    public Integer getCellTerm() {
        return this.cellDetail.term;
    }

    public Integer getTakeMaxTerm() {
        Integer max = 0;
        for (TakeDetail takeDetail : takeDetails) {
            if (takeDetail.term != null && takeDetail.term > max) {
                max = takeDetail.term;
            }
        }
        return max;
    }

    public Integer getFirstDamageSum() {
        Integer sum = 0;
        for (TakeDetail takeDetail : takeDetails) {
            if (takeDetail.firstDamage != null) {
                sum += takeDetail.firstDamage;
            }
        }
        return sum;
    }

    public Integer getToChangeSum() {
        Integer sum = 0;
        for (TakeDetail takeDetail : takeDetails) {
            if (takeDetail.toChange != null) {
                sum += takeDetail.toChange;
            }
        }
        return sum;
    }

}
