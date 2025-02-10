package common.model.information;

public class DetailSummary {

    Integer cellVacuumed = 0;
    Integer anodesMachined = 0;
    Integer anodesHandled = 0;
    Integer anodesChanged = 0;

    public void incCellVacuumed(int vacuuned) {
        this.cellVacuumed += vacuuned;
    }

    public void incAnodesMachined(final int machined) {
        this.anodesMachined += machined;
    }

    public void incAnodesHandled(final int handled) {
        this.anodesHandled += handled;
    }

    public void incAnodesChanged(final int changed) {
        this.anodesChanged += changed;
    }

}
