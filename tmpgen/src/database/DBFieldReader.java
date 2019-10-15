package database;

import config.BeanListReader;
import config.ParaData;
import org.apache.velocity.VelocityContext;
import util.TmpGenProperties;

import java.sql.*;
import java.util.*;

public class DBFieldReader implements BeanListReader {
    public static final Map<String, List> fieldListMap = new HashMap<String, List>();

    public List getBeanList(VelocityContext context) {
        ParaData paraData = (ParaData) context.get("tablename");
        String tableName = paraData.getValue();
        List fieldList = fieldListMap.get(tableName);
        if (fieldList != null) {
            return fieldList;
        }
        fieldList = getBeanList(paraData.getValue());
        fieldListMap.put(tableName, fieldList);
        return fieldList;
    }

    public List getBeanList(String tableName) {

        List<DBField> list = new ArrayList<DBField>();
        Connection conn = null;
        ResultSet rs = null;
        ResultSet rsPK = null;
        try {
            conn = getConnection();
            DatabaseMetaData metaData = conn.getMetaData();

            rs = metaData.getColumns(null, null, tableName, null);
            while (rs.next()) {
//                String name = rs.getString("table_name");
                String columnName = rs.getString("column_name");
                String columnType = rs.getString("TYPE_NAME");
                if (columnType != null && columnType.contains("identity")) {
                    columnType = columnType.replaceAll("identity", "").trim();
                }
                String columnComment = rs.getString("REMARKS");

                DBField dbField = new DBField();
                dbField.setName(columnName);
                dbField.setType(convertDBType(columnType.toUpperCase()));
                dbField.setDesc(columnComment);
                list.add(dbField);
            }
            rsPK = metaData.getPrimaryKeys(null, null, tableName);
            while (rsPK.next()) {
                String columnName = rsPK.getString("column_name");

                for (DBField dbField : list) {
                    if (dbField.getName().equalsIgnoreCase(columnName)) {
                        dbField.setPK(true);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (rsPK != null) {
                try {
                    rsPK.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return list;
    }

    private String convertDBType(String dbType) {
        return dbType.replaceAll(" ", "_");
    }

    public Connection getConnection() throws Exception {
        Connection conn;
        String driver = TmpGenProperties.getProperties("db.driver");
        String connectionURL = TmpGenProperties.getProperties("db.connection");
        String userName = TmpGenProperties.getProperties("db.user");
        String password = TmpGenProperties.getProperties("db.pwd");
        Class.forName(driver);

        Properties props =new Properties();
        props.setProperty("user",userName);
        props.setProperty("password",password);
        props.setProperty("remarksReporting","true");
        conn = DriverManager.getConnection(connectionURL, props);
//        conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}
