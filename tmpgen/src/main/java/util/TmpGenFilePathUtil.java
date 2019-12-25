package util;

public class TmpGenFilePathUtil {
    public static final String BASEPATH = ".";

    public static String getBasePath() {
        return BASEPATH;
    }

    public static String getConfigFile() {
        String tempFile = TmpGenProperties.getProperties("file.configuration.file");
        if (tempFile == null || tempFile.trim().equals("")) {
            tempFile = BASEPATH + "\\config\\ParameterConfig.xml";
        }
        return tempFile;
    }

    public static String getTemplatePath() {
        String tempPath = TmpGenProperties.getProperties("file.template.path");
        if (tempPath == null || tempPath.trim().equals("")) {
            tempPath = BASEPATH + "\\template";
        }
        return tempPath;
    }

    public static String getOutPath() {
        String tempPath = TmpGenProperties.getProperties("file.out.path");
        if (tempPath == null || tempPath.trim().equals("")) {
            tempPath = BASEPATH + "\\out";
        }
        return tempPath;
    }
}