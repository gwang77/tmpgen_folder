import config.BeanListReader;
import config.ParaData;
import config.ParaDataListBean;
import config.ParameterConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import util.TmpGenFilePathUtil;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Process {
    VelocityContext context;

    public Process() {
        initialVelocity();
    }

    public void doProcess(String tmpPath, String outPath) {
        Map splitParaMap = ParameterConfig.instance().getSplitParameterMap();
        String maxLengthStr = (String) context.get("SPLIT_ARR_MAX_LENGTH");
        maxLengthStr = StringUtils.isBlank(maxLengthStr) ? "1" : maxLengthStr;
        int maxLength = Integer.parseInt(maxLengthStr);

        for (int m = 0; m < maxLength; m++) {
            Set keySetTmp = splitParaMap.keySet();
            for (Object aKeySetTmp : keySetTmp) {
                String key = (String) aKeySetTmp;
                String[] valueArr = (String[]) context.get(key + "_ARR");
                context.put(key, new ParaData(valueArr[m]));
            }
            prepareBeanList(context);
            doProcessFolder(tmpPath, outPath);
        }
    }

    public void doProcessFolder(String tmpPath, String outPath) {
        long t1 = System.currentTimeMillis();
        System.out.println("doProcess:" + t1 + " " + tmpPath);
        File fp = new File(tmpPath);
        File[] files = fp.listFiles();

        for (int i = 0; files != null && i < files.length; i++) {
            File fTmp = files[i];
            if (isIgnoreFile(fTmp.getName())) {
                continue;
            }
            String newFileFolderName = processFileFolderName(fTmp.getName());
            if (fTmp.isDirectory()) {
                String newTmpPath = tmpPath + "\\" + fTmp.getName();
                String newOutPath = outPath + "\\" + newFileFolderName;
                saveFolder(newOutPath);
                doProcessFolder(newTmpPath, newOutPath);
            } else {
                String filePath = tmpPath + "\\" + fTmp.getName();
                String newOutPath = outPath + "\\" + newFileFolderName;
                processFile(filePath, newOutPath);
            }
        }
    }

    public String processFileFolderName(String fileFolderName) {
        Writer writer;
        String tmpFileName = String.valueOf(System.currentTimeMillis());
        String tmpFilePath = TmpGenFilePathUtil.BASEPATH + "/" + tmpFileName;
        String newName = fileFolderName;
        try {
            FileManage.saveFile(fileFolderName, tmpFilePath);
            Template template = Velocity.getTemplate(tmpFileName);
            writer = new StringWriter();
            template.merge(context, writer);
            newName = writer.toString();
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileManage.delete(tmpFilePath);
        }

        return newName;
    }

    public void saveFolder(String outpath) {
        FileManage.createFolder(outpath);
    }

    public void processFile(String filePath, String outPath) {
        Writer writer;

        try {
            Template template = Velocity.getTemplate(filePath, "UTF-8");
            writer = new BufferedWriter(new FileWriter(outPath));
            template.merge(context, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialVelocity() {
        try {
            Velocity.init("velocity.properties");
        } catch (Exception e) {
            System.out.println("Problem initializing Velocity : " + e);
        }
        context = new VelocityContext();

        Map paraMap = ParameterConfig.instance().getParameterMap();
        Map splitParaMap = ParameterConfig.instance().getSplitParameterMap();
        Set keySet = paraMap.keySet();
        for (Object aKeySet : keySet) {
            String key = (String) aKeySet;
            Object val = paraMap.get(key);
            String split = (String) splitParaMap.get(key);
            split = StringUtils.isBlank(split) ? "" : split;
            if (val instanceof String) {
                context.put(key, new ParaData((String) val, split));
                if (StringUtils.isNotBlank(split)) {
                    initialSplitParameters(key, (String) val, split);
                }
            } else {
                context.put(key, val);
            }
        }
    }

    public void initialSplitParameters(String key, String val, String split) {
        String maxLengthStr = (String) context.get("SPLIT_ARR_MAX_LENGTH");
        maxLengthStr = StringUtils.isBlank(maxLengthStr) ? "1" : maxLengthStr;
        int maxLength = Integer.parseInt(maxLengthStr);
        String[] arr = (String[]) context.get(key + "_ARR");
        if (arr != null) {
            if (maxLength == 1 || arr.length < maxLength) {
                maxLength = arr.length;
            }
        }

        String[] valueArr = String.valueOf(val).split(split);
        if (maxLength == 1 || valueArr.length < maxLength) {
            maxLength = valueArr.length;
        }
        context.put(key + "_ARR", valueArr);
        context.put("SPLIT_ARR_MAX_LENGTH", String.valueOf(maxLength));
    }

    public boolean isIgnoreFile(String fileName) {
        ParaData ignoreFiles = (ParaData) context.get("ignorefiles");
        String[] files = ignoreFiles.getValue().split(",");
        for (String file : files) {
            if (fileName.equalsIgnoreCase(file)) {
                return true;
            }
        }
        return false;
    }

    public void prepareBeanList(VelocityContext context) {
        List nodeList = (List) context.get("beanList");
        try {
            for (Object aNodeList : nodeList) {
                ParaDataListBean bean = (ParaDataListBean) aNodeList;
                String keyName = bean.getName();
                String className = bean.getListClass();
                if (className != null && !className.trim().equals("")) {
                    BeanListReader reader = (BeanListReader) Class.forName(className).newInstance();
                    List beanList = reader.getBeanList(context);
                    if (beanList == null) {
                        continue;
                    }
                    context.put(keyName, beanList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
