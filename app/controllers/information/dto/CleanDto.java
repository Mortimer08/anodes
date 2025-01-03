package controllers.information.dto;

import models.information.TakeDetail;

import java.util.Date;
import java.util.List;

public class CleanDto {

    public Date date;
    public List<Long> cell;
    public List<Long> take;

    public List<CellDetailDto> cellClean;
    public List<TakeDetail> takeClean;

}
