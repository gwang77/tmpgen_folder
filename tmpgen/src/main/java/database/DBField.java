package database;

import process.TmpGenUtil;
import util.TmpGenProperties;

public class DBField
{
    private String name;
    private String type;
    private int length;
    private boolean isPK = false;
    private String desc = "";

    private static String ignorefields = TmpGenProperties.getProperties("ignorefields").toUpperCase();

    public DBField()
    {
    }
    public DBField(String name, String type, int length)
    {
        this.name = name;
        this.type = type;
        this.length = length;
    }

    public DBField(String name, String type, int length, boolean PK)
    {
        this.name = name;
        this.type = type;
        this.length = length;
        isPK = PK;
    }

    public DBField(String name, String type, int length, boolean PK, String desc)
    {
        this.name = name;
        this.type = type;
        this.length = length;
        isPK = PK;
        this.desc = desc;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public boolean isPK()
    {
        return isPK;
    }

    public void setPK(boolean PK)
    {
        isPK = PK;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getNameLow()
    {
        return name.toLowerCase();
    }

    public String getNameLowFirstUp()
    {
        String first = getNameLow().substring(0, 1);
        return getNameLow().replaceFirst(first, first.toUpperCase());
    }

    public String getNameUp()
    {
        return name.toUpperCase();
    }

    public String getNameFirstUp()
    {
        String first = name.substring(0, 1);
        return name.replaceFirst(first, first.toUpperCase());
    }

    public String getNameFirstLow()
    {
        String first = name.substring(0, 1);
        return name.replaceFirst(first, first.toLowerCase());
    }

    public String getNameFirstLowWithType()
    {
        String nameTmp = getNameFirstLow();
        if(!isIgnored()) {
            if (TmpGenUtil.isDate(getType())) {
                nameTmp = nameTmp + "_d";
            }
            if (TmpGenUtil.isInt(getType()) || TmpGenUtil.isDouble(getType())) {
                nameTmp = nameTmp + "_n";
            }
        }
        return nameTmp;
    }

    public String getLowVal()
    {
        return name.toLowerCase();
    }

    public String getUpVal()
    {
        return name.toUpperCase();
    }

    public boolean isIgnored() {
        if (ignorefields != null) {
            return ignorefields.indexOf("," + name.toUpperCase() + ",") >= 0;
        }
        return false;
    }
}
