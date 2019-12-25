package database;

import config.BeanListReader;
import excel.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import util.TmpGenProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelDBFieldReader implements BeanListReader {
    public static final Map<String, List> fieldListMap = new HashMap<String, List>();

    public List getBeanList(VelocityContext context) {
        String excelPath = TmpGenProperties.getProperties("excel.path");
        String sheetName = TmpGenProperties.getProperties("excel.sheet");
        String nameColName = TmpGenProperties.getProperties("excel.field.name.col");
        String typeColName = TmpGenProperties.getProperties("excel.field.type.col");
        String lengthColName = TmpGenProperties.getProperties("excel.field.length.col");
        String isPkColName = TmpGenProperties.getProperties("excel.field.ispk.col");

        List fieldList = fieldListMap.get(sheetName);
        if (fieldList != null) {
            return fieldList;
        }
        fieldList = getExcelFieldList(excelPath, sheetName, nameColName, typeColName, lengthColName, isPkColName);
        fieldListMap.put(sheetName, fieldList);
        return fieldList;
    }

    private List<DBField> getExcelFieldList(String excelPath, String sheetName, String nameColName, String typeColName, String lengthColName, String isPkColName) {
        List<DBField> fieldList = new ArrayList<DBField>();
        List<Map> mapList = ExcelUtil.getExcelFieldList(excelPath, sheetName);
        for (Map map : mapList) {
            String name = (String) map.get(nameColName);
            String type = (String) map.get(typeColName);
            String lengthStr = (String) map.get(lengthColName);
            String isPK = (String) map.get(isPkColName);
            if (StringUtils.isBlank(name) && StringUtils.isBlank(type) && StringUtils.isBlank(lengthStr) && StringUtils.isBlank(isPK)) {
                break;
            }
            if (isIgnoreExcelVal(name, type)) {
                continue;
            }
            lengthStr = StringUtils.isBlank(lengthStr) ? "0" : lengthStr;
            isPK = StringUtils.isBlank(isPK) ? "false" : isPK;
            fieldList.add(new DBField(name, type, Integer.parseInt(lengthStr), Boolean.parseBoolean(isPK)));
        }
        return fieldList;
    }

    private boolean isIgnoreExcelVal(String str1, String str2) {
        return StringUtils.isBlank(str1) || StringUtils.isBlank(str2);
    }
}
