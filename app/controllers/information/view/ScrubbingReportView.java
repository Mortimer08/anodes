package controllers.information.view;

import play.i18n.Messages;

public enum ScrubbingReportView {

    DATE("happened", "ScrubbingReportView.happened"),
    TAKE("take", "ScrubbingReportView.take"),
    FIRST_DAMAGE("firstDamage", "ScrubbingReportView.firstDamage"),
    TO_CHANGE("toChange", "ScrubbingReportView.toChange"),
    MACHINED("machined", "ScrubbingReportView.machined"),
    HANDLED("handled", "ScrubbingReportView.handled"),
    CHANGED("changed", "ScrubbingReportView.changed"),
    COMMENT("comment", "ScrubbingReportView.comment"),
    ;

    public String name;
    public String title;

    ScrubbingReportView(String name, String title) {
        this.name = name;
        this.title = title;
    }

    @Override
    public String toString() {
        return Messages.get(title);
    }
}
