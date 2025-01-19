package controllers.information.report;

import common.Data;
import common.utils.PoiUtils;
import controllers.information.view.VacuumingReportView;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.persistence.Tuple;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static common.utils.PoiUtils.*;

public class VacuumingReport {

    public static final String REPORT_NAME = "vacuuming";
    public static final String REPORT_SHEET_NAME = "Vacuuming";
    public static final int[] grid = {16, 16, 16};

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
        for (VacuumingReportView view : VacuumingReportView.values()) {
            newCell(view.toString(), header, row);
        }
        for (Tuple item : items) {
            row = newRow(sheet, 2);
            newCell(item.get(VacuumingReportView.DATE.name, Date.class), center, row);
            newCell(item.get(VacuumingReportView.CELL.name, String.class), center, row);
            newCell(item.get(VacuumingReportView.COMMENT.name, String.class), left, row);
        }
        try {
            PoiUtils.write(wb, file);
        } catch (IOException ignored) {
        }
        return fileName;

    }
}
