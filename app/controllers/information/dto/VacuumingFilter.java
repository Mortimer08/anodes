package controllers.information.dto;

import controllers.information.view.VacuumingView;
import controllers.util.DateUtils;
import models.Team;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class VacuumingFilter implements Serializable {

    public List<Integer> teamIndex;
    public Date begin;
    public Date end;
    public String sort;
    public String order;

    public VacuumingFilter() {
        teamIndex = Team.getIndexes();
        final Date today = new Date();
        begin = DateUtils.monthFirstDate(today);
        end = DateUtils.monthLastDate(today);
        this.sort = VacuumingView.HAPPENED.name;
        this.order = "desc";
    }

    public VacuumingFilter(List<Integer> teamIndex, Date begin, Date end) {
        if (teamIndex == null) {
            teamIndex = List.of(Team.values().length);
        }
        this.teamIndex = teamIndex;
        this.begin = begin;
        this.end = end;
        this.sort = VacuumingView.HAPPENED.name;
        this.order = "desc";
    }

    public void setSort(final VacuumingView view) {
        updateOrder();
        this.sort = view.name;
    }

    public void updateOrder() {
        this.order = this.order == null ? "desc" : (this.order.equals("asc") ? "desc" : "asc");
    }

    public void updateIndex(List<Integer> teamIndex) {
        this.teamIndex = teamIndex;
    }

    public void updateDates(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

}
