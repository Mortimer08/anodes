package models.information;

import models.ground.Cell;

import java.util.Date;

public class CellStatus {

    public Cell cell;
    public Date vacuumed;
    public int term;
    public String comment;

    public CellStatus(Cell cell) {
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
}
