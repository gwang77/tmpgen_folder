package config;

public class ParaDataListBean {
    private String name;
    private String listClass;

    public ParaDataListBean(String name, String listClass) {
        this.name = name;
        this.listClass = listClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListClass() {
        return listClass;
    }

    public void setListClass(String listClass) {
        this.listClass = listClass;
    }
}
