import util.TmpGenFilePathUtil;

public class TmpGen {
    public static void main(String[] args) {
        String tmpPath = TmpGenFilePathUtil.getTemplatePath();
        String outPath = TmpGenFilePathUtil.getOutPath();

        long t1 = System.currentTimeMillis();
        Process process = new Process();
        process.doProcess(tmpPath, outPath);
        long t2 = System.currentTimeMillis();

        System.out.println("Total Executed time:" + (t2 - t1));
        System.out.println("Out Path:" + outPath);
        System.out.println("Generate Template OK.");
    }
}
