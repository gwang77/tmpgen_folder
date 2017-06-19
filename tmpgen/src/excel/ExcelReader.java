package excel;

import config.BeanListReader;
import org.apache.velocity.VelocityContext;
import util.TmpGenProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader implements BeanListReader {
    public static final Map<String, List> beanListMap = new HashMap<String, List>();

    public List getBeanList(VelocityContext context) {
        String excelPath = TmpGenProperties.getProperties("excel.path");
        String sheetName = TmpGenProperties.getProperties("excel.sheet");

        List fieldList = beanListMap.get(sheetName);
        if (fieldList != null) {
            return fieldList;
        }

        fieldList = ExcelUtil.getExcelFieldList(excelPath, sheetName);
        beanListMap.put(sheetName, fieldList);
        return fieldList;
    }
}
