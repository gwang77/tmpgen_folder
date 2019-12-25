package config;

import database.DBField;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import util.TmpGenFilePathUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParameterConfig {
    private static ParameterConfig _instance;
    private static HashMap parameterMap = new HashMap();
    private static HashMap splitParameterMap = new HashMap();

    public static ParameterConfig instance() {
        if (_instance == null) {
            _instance = new ParameterConfig();
            _instance.initial();
        }

        return _instance;
    }

    public HashMap getParameterMap() {
        return parameterMap;
    }

    public HashMap getSplitParameterMap() {
        return splitParameterMap;
    }

    public String getParameterValue(String name) {
        if (parameterMap == null) {
            parameterMap = new HashMap();
        }
        return (String) parameterMap.get(name);
    }

    public String getSplitParameterValue(String name) {
        if (splitParameterMap == null) {
            splitParameterMap = new HashMap();
        }
        return (String) splitParameterMap.get(name);
    }

    private void initial() {
        parameterMap = null;
        parameterMap = new HashMap();

        try {
            DocumentBuilder docbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docbuilder.parse(TmpGenFilePathUtil.getConfigFile());
            NodeList list = doc.getElementsByTagName("parameter");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                NamedNodeMap nodeMap = node.getAttributes();

                Node subNode1 = nodeMap.getNamedItem("name");
                String name = subNode1.getNodeValue();
                Node subNode2 = nodeMap.getNamedItem("value");
                String value = subNode2.getNodeValue();
                parameterMap.put(name, value);

                String split = "";
                Node subNode3 = nodeMap.getNamedItem("split");
                if (subNode3 != null) {
                    split = subNode3.getNodeValue();
                }
                if (StringUtils.isNotBlank(split)) {
                    splitParameterMap.put(name, split);
                }
            }
            List nodeBeanList = new ArrayList();
            NodeList nodeList = doc.getElementsByTagName("beanList");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                NamedNodeMap nodeMap = node.getAttributes();

                Node subNameNode = nodeMap.getNamedItem("name");
                Node subClassNode = nodeMap.getNamedItem("listClass");
                String keyName = subNameNode.getNodeValue();
                String className = subClassNode.getNodeValue();
                if (className != null && !className.trim().equals("")) {
                    nodeBeanList.add(new ParaDataListBean(keyName, className));
                }
            }

            parameterMap.put("beanList", nodeBeanList);

            NodeList fieldList = doc.getElementsByTagName("fields");
            List fields = getFields(fieldList);

            parameterMap.put("fieldList", fields);

            NodeList fieldListProcess = doc.getElementsByTagName("process");

            initProcesses(fieldListProcess);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initProcesses(NodeList nodeList) {

        try {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = (Node) nodeList.item(i);
                NamedNodeMap nodeMap = node.getAttributes();

                Node subNode1 = nodeMap.getNamedItem("name");
                String name = subNode1.getNodeValue();
                Node subNode2 = nodeMap.getNamedItem("classpath");
                String value = subNode2.getNodeValue();
                Object instance = null;
                if (value != null && !"".equals(value.trim())) {
                    Class clazz = Class.forName(value);
                    instance = clazz.newInstance();
                }
                if (name != null && instance != null) {
                    parameterMap.put(name, instance);
                }
            }
        } catch (Exception e) {
            System.out.println("err:" + e.getMessage());
        }

    }

    public ArrayList getFields(NodeList nodeList) {
        ArrayList list = new ArrayList();
        try {
//            DocumentBuilder docbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            Document doc = docbuilder.parse(TmpGenUtil.getConfigPath() + "\\FieldConfig.xml");
//            NodeList nodeList = doc.getElementsByTagName("field");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node fields = nodeList.item(i);
                NodeList fieldList = fields.getChildNodes();
                for (int j = 0; j < fieldList.getLength(); j++) {
                    Node node = fieldList.item(j);
                    String nodeName = node.getNodeName();
                    if (!nodeName.trim().equals("field")) {
                        continue;
                    }

                    NamedNodeMap nodeMap = node.getAttributes();

                    String name = "";
                    String type = "";
                    int length = 0;
                    boolean isPk = false;
                    String desc = "";
                    Node subNode1 = nodeMap.getNamedItem("name");

                    name = subNode1.getNodeValue();
                    Node subNode2 = nodeMap.getNamedItem("type");
                    if (subNode2 != null) {
                        type = subNode2.getNodeValue();
                    }
                    Node subNode3 = nodeMap.getNamedItem("length");
                    if (subNode3 != null) {
                        String sTmp = subNode3.getNodeValue();
                        if (sTmp == null || sTmp.trim().equals("")) {
                            length = 0;
                        } else {
                            length = Integer.parseInt(sTmp);
                        }
                    }
                    Node subNode4 = nodeMap.getNamedItem("ispk");
                    if (subNode4 != null) {
                        String sTmp = subNode4.getNodeValue();
                        if (sTmp == null || sTmp.trim().equals("")) {
                            isPk = false;
                        } else {
                            isPk = Boolean.valueOf(sTmp).booleanValue();
                        }
                    }
                    Node subNode5 = nodeMap.getNamedItem("desc");
                    if (subNode5 != null) {
                        desc = subNode5.getNodeValue();
                        byte[] bt = desc.getBytes("iso-8859-1");
                        desc = new String(bt);
                    }
                    list.add(new DBField(name, type, length, isPk, desc));
                }
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
