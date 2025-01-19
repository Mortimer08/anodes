package controllers.information.view;

import play.i18n.Messages;

public enum VacuumingReportView {

    DATE("happened", "VacuumingReportView.happened"),
    CELL("cell", "VacuumingReportView.cell"),
    COMMENT("comment", "VacuumingReportView.comment"),
    ;

    public String name;
    public String title;

    VacuumingReportView(String name, String title) {
        this.name = name;
        this.title = title;
    }

    @Override
    public String toString() {
        return Messages.get(title);
    }
}
