package common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class PoiUtils {

    public static String getReportName(String name) {
        final String code = UUID.randomUUID().toString().substring(0, 6).toLowerCase();
        return String.format("%s_%s_%s.xlsx", name, LocalDateTime.now().toString("MMddHHsss"), code);
    }

    public static void write(Workbook workbook, File file) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
        }
    }

    public static XSSFSheet createSheet(XSSFWorkbook wb, String name, int[] grid) {
        XSSFSheet sheet = wb.createSheet(name);
        setGrid(sheet, grid);
        return sheet;
    }

    public static void setGrid(Sheet sheet, int[] grid) {
        for (int j = 0, gridLength = grid.length; j < gridLength; j++)
            sheet.setColumnWidth(j, grid[j] * 256);
    }

    public static XSSFRow getRow(XSSFSheet sheet, int height) {
        XSSFRow row = sheet.createRow(sheet.getLastRowNum());
        setHeight(row, height);
        return row;
    }

    public static XSSFRow newRow(XSSFSheet sheet, int height) {
        XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
        setHeight(row, height);
        return row;
    }

    public static void setHeight(XSSFRow row, int height) {
        row.setHeight((short) (height * 256));
    }

    public static Cell newCell(String value, CellStyle body, XSSFRow row) {
        short cellNum = row.getLastCellNum();
        if (cellNum == -1) cellNum = 0;
        Cell cell = row.createCell(cellNum);
        cell.setCellStyle(body);
        cell.setCellValue(StringUtils.defaultString(value));
        return cell;
    }

    public static Cell newCell(Integer value, CellStyle body, XSSFRow row) {
        short cellNum = row.getLastCellNum();
        if (cellNum == -1) cellNum = 0;
        Cell cell = row.createCell(cellNum);
        cell.setCellStyle(body);
        if (value != null) cell.setCellValue(value);
        return cell;
    }

    public static Cell newCell(Date value, CellStyle body, XSSFRow row) {
        short cellNum = row.getLastCellNum();
        if (cellNum == -1) cellNum = 0;
        Cell cell = row.createCell(cellNum);
        cell.setCellStyle(body);
        cell.setCellValue(value == null ? "" : new LocalDate(value).toString("dd.MM.yyyy"));
        return cell;
    }

    public static CellStyle getHeaderStyle(XSSFWorkbook wb) {
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setBold(true);
        font.setFontName("Calibri");
        XSSFCellStyle cellStyle = wb.createCellStyle();
        XSSFColor myColor = new XSSFColor(new java.awt.Color(223, 235, 247));
        cellStyle.setFillForegroundColor(myColor);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFont(font);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    public static CellStyle getLeftStyle(XSSFWorkbook wb) {
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("Calibri");
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        return cellStyle;
    }

    public static CellStyle getCenterStyle(XSSFWorkbook wb) {
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("Calibri");
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    public static CellStyle getRightStyle(XSSFWorkbook wb) {
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("Calibri");
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        return cellStyle;
    }

}
