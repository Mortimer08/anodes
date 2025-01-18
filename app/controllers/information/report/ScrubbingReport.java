package controllers.information.report;

import common.Data;
import common.utils.PoiUtils;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import controllers.information.view.ScrubbingReportView;
import models.Team;
import models.ground.Take;
import models.ground.TakeNumber;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static common.utils.PoiUtils.*;

public class ScrubbingReport {

    public static final String REPORT_NAME = "scrubbing";
    public static final String REPORT_SHEET_NAME = "Scrubbing";
    public static final int[] grid = {16, 16, 16, 16, 16, 16, 16, 16};

    public static String create(List<Tuple> items) {
        String fileName = PoiUtils.getReportName(REPORT_NAME);
        final File file = new File(Data.DOWNLOAD, fileName);
        final XSSFWorkbook wb = new XSSFWorkbook();
        final XSSFSheet sheet = PoiUtils.createSheet(wb, REPORT_SHEET_NAME, grid);

        final CellStyle header = getHeaderStyle(wb);
        final CellStyle left = getLeftStyle(wb);
        final CellStyle center = getCenterStyle(wb);
        final CellStyle right = getRightStyle(wb);

        XSSFRow row = getRow(sheet, 2);
        for (ScrubbingReportView view : ScrubbingReportView.values()) {
            newCell(view.toString(), header, row);
        }

        for (Tuple item : items) {
            row = newRow(sheet, 2);
            newCell(item.get(ScrubbingReportView.DATE.name, Date.class), center, row);
            newCell(item.get(ScrubbingReportView.TAKE.name, String.class), center, row);
            final int firstDamage = zeroIfNullOrNegative(item.get(ScrubbingReportView.FIRST_DAMAGE.name, Integer.class));
            newCell(firstDamage, center, row);
            final int toChange = zeroIfNullOrNegative(item.get(ScrubbingReportView.TO_CHANGE.name, Integer.class));
            newCell(toChange, center, row);
            final int machined = zeroIfNullOrNegative(item.get(ScrubbingReportView.MACHINED.name, Integer.class));
            newCell(machined, center, row);
            Integer number = (item.get("number", Integer.class));
            final int handled = zeroIfNullOrNegative(TakeNumber.values()[number].quantity - machined);
            newCell(handled, center, row);
            final int changed = zeroIfNullOrNegative(item.get(ScrubbingReportView.CHANGED.name, Integer.class));
            newCell(changed, center, row);
            newCell(item.get(ScrubbingReportView.COMMENT.name, String.class), center, row);
        }

        try {
            PoiUtils.write(wb, file);
        } catch (IOException ignored) {
        }
        return fileName;
    }

    private static int zeroIfNullOrNegative(final Integer number) {
        return (number == null || number < 0) ? 0 : number;
    }
}
