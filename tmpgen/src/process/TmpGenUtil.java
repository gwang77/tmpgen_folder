package process;

import util.TmpGenProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class TmpGenUtil {
    private static Map<String, Object> DB_FIELD_TYPE_MAPPING = null;
    private static Map<String, Object> DB_FIELD_JDBC_TYPE_MAPPING = null;

    private static final String INT[] = TmpGenProperties.getProperties("db.field.type.group.INT").split(",");
    private static final String DOUBLE[] = TmpGenProperties.getProperties("db.field.type.group.DOUBLE").split(",");
    private static final String DATE[] = TmpGenProperties.getProperties("db.field.type.group.DATE").split(",");
    private static final String STRING[] = TmpGenProperties.getProperties("db.field.type.group.STRING").split(",");
    private static final String BYTEARRAY[] = TmpGenProperties.getProperties("db.field.type.group.BYTEARRAY").split(",");

    public static boolean isInt(String fieldType) {
        return isType(fieldType, INT);
    }

    public static boolean isString(String fieldType) {
        return isType(fieldType, STRING);
    }

    public static boolean isDate(String fieldType) {
        return isType(fieldType, DATE);
    }

    public static boolean isDouble(String fieldType) {
        return isType(fieldType, DOUBLE);
    }

    public static boolean isByteArr(String fieldType) {
        return isType(fieldType, BYTEARRAY);
    }

    private static boolean isType(String fieldType, String[] typeArr) {
        for (String aTypeArr : typeArr) {
            if (aTypeArr.equals(fieldType)) {
                return true;
            }
        }
        return false;
    }

    public static void initDBFieldTypeMapping() {
        DB_FIELD_TYPE_MAPPING = new HashMap<String, Object>();
        DB_FIELD_JDBC_TYPE_MAPPING = new HashMap<String, Object>();
        Properties properties = TmpGenProperties.properties;
        Set set = properties.entrySet();
        for (Object aSet : set) {
            Map.Entry entry = (Map.Entry) aSet;
            String key = (String) entry.getKey();
            if (key.contains("db.field.type.mapping.")) {
                String dbType = key.substring(22);
                String val = (String) entry.getValue();
                String[] vals = val.split(",");
                String javaType = vals[0];
                String jdbcType = vals.length >= 2 ? vals[1] : dbType;
                DB_FIELD_TYPE_MAPPING.put(dbType, javaType);
                DB_FIELD_JDBC_TYPE_MAPPING.put(dbType, jdbcType);
            }
        }
    }

    public static String getFieldJavaTypeFull(String fieldType) {
        if (DB_FIELD_TYPE_MAPPING == null) {
            initDBFieldTypeMapping();
        }
        String type = (String) DB_FIELD_TYPE_MAPPING.get(fieldType.toUpperCase());
        if (type == null) {
            System.out.println("Cannot find configuration of DB type:" + fieldType + ". Please configure it in tmpgen.properties");
            type = "";
        }
        return type;
    }

    public static String getFieldJavaType(String fieldType) {
        String type = getFieldJavaTypeFull(fieldType);
        if (type != null && type.length() > 0) {
            int idx = type.lastIndexOf(".");
            if (idx >= 0) {
                type = type.substring(idx + 1);
            }
        }
        return type;
    }

    public static String getFieldJDBCType(String fieldType) {
        if (DB_FIELD_JDBC_TYPE_MAPPING == null) {
            initDBFieldTypeMapping();
        }
        String type = (String) DB_FIELD_JDBC_TYPE_MAPPING.get(fieldType.toUpperCase());
        if (type == null) {
            System.out.println("Cannot find configuration of DB type:" + fieldType + ". Please configure it in tmpgen.properties");
            type = "";
        }
        return type;
    }

    public static String getFieldJavaTypeForPWF(String fieldType) {
        return getFieldJavaTypeFull(fieldType);
    }

    public static String getFieldGetMethod(String fieldType) {
        String result = "";
        if (TmpGenUtil.isInt(fieldType)) {
            result = "getInt";
        } else if (TmpGenUtil.isDouble(fieldType)) {
            result = "getDouble";
        } else if (TmpGenUtil.isDate(fieldType)) {
            result = "getDate";
        } else if (TmpGenUtil.isString(fieldType)) {
            result = "getString";
        } else if (TmpGenUtil.isByteArr(fieldType)) {
            result = "getByte";
        }
        return result;
    }

    public String getValLow(String val) {
        return val.toLowerCase();
    }

    public String getValUp(String val) {
        return val.toUpperCase();
    }

    public String getValLowFirstUp(String val) {
        String first = getValLow(val).substring(0, 1);
        return getValLow(val).replaceFirst(first, first.toUpperCase());
    }

    public String getValFirstUp(String val) {
        String first = val.substring(0, 1);
        return val.replaceFirst(first, first.toUpperCase());
    }

    public String getValFirstLow(String val) {
        String first = val.substring(0, 1);
        return val.replaceFirst(first, first.toLowerCase());
    }

}
