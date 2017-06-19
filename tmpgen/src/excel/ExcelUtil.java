package excel;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    public static List<Map> getExcelFieldList(String excelPath, String sheetName) {
        List<Map> beanList = new ArrayList<Map>();
        try {
            InputStream is = new FileInputStream(excelPath);
            Workbook wb = WorkbookFactory.create(is);
            Sheet sheet = wb.getSheet(sheetName);
            int rowID = 0;

            List<String> colNameList = new ArrayList<String>();
            Row firstRow = sheet.getRow(rowID);
            int colIdx = 0;
            Cell cell = firstRow.getCell(colIdx);
            while (cell != null && StringUtils.isNotBlank(getCellValue(cell))) {
                colNameList.add(getCellValue(cell));
                colIdx++;
                cell = firstRow.getCell(colIdx);
            }

            rowID = 1;
            int totalRow = sheet.getPhysicalNumberOfRows();
            while (true) {
                Row row = sheet.getRow(rowID);
                if (row == null || rowID > totalRow) {
                    break;
                }
                Map<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < colNameList.size(); i++) {
                    Cell cellName = row.getCell(i);
                    String val = getCellValue(cellName);
                    map.put(colNameList.get(i), val);
                }

                String firstVal = map.get(colNameList.get(0));
                if (StringUtils.isBlank(firstVal)) {
                    break;
                }
                beanList.add(map);

                rowID++;
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanList;
    }

    public static String getCellValue(Cell cell) {
        String value = "";
        String format;
        double dValue;
        format = cell.getCellStyle().getDataFormatString();
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                dValue = cell.getNumericCellValue();
                if (StringUtils.equals(format, "General") || StringUtils.equals(format, "@")) {
                    value = new DecimalFormat("0.###############").format(dValue);
                } else {
                    format = StringUtils.replace(format, "+", "");
                    format = StringUtils.replace(format, "_", "");
                    DecimalFormat df = new DecimalFormat(format);

                    value = df.format(dValue);
                    value = StringUtils.replace(value, "E", "E+");
                }

                break;
            case Cell.CELL_TYPE_STRING:
                if (cell.getStringCellValue() != null)
                    value = StringUtils.trim(cell.getStringCellValue());
                break;
            default:
        }

        return value.trim();
    }
}