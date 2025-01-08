package controllers.information.dto;

import common.dto.BaseFilter;
import controllers.information.view.ScrubbingView;
import controllers.util.DateUtils;
import models.Team;

import java.util.Date;
import java.util.List;

public class ScrubbingFilter extends BaseFilter {

    public List<Integer> teamIndex;
    public Date begin;
    public Date end;

    public ScrubbingFilter() {
        teamIndex = Team.getIndexes();
        final Date today = new Date();
        begin = DateUtils.monthFirstDate(today);
        end = DateUtils.monthLastDate(today);
        this.sort = ScrubbingView.HAPPENED.name;
        this.order = "desc";
    }

    public void updateIndex(List<Integer> teamIndex) {
        this.teamIndex = teamIndex;
    }

    public void updateDates(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    public void setSort(final ScrubbingView sort) {
        updateOrder();
        this.sort = sort.name;
    }
}
