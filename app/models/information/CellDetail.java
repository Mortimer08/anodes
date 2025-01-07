package models.information;

import models.ground.Cell;

import java.util.Date;

public class CellDetail {

    public Cell cell;
    public Date vacuumed;
    public Integer term;
    public String comment;
    public Boolean checked = false;

    public CellDetail(Cell cell) {
        this.cell = cell;
    }

    public void setVacuumed(Date vacuumed) {
        this.vacuumed = vacuumed;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void setComment(String comment) {
        this.comment = comment != null ? comment : "";
    }

    public boolean isVacuumed() {
        return this.cell != null && this.checked;
    }
}
