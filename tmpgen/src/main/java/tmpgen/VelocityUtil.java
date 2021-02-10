package tmpgen;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;

public class VelocityUtil {
    public static String convertTemplate(String str, VelocityContext context) {
        try {
            //设置输出
            StringWriter writer = new StringWriter();
            //将环境数据转化输出
            Velocity.evaluate(context, writer, "mystring", str);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
