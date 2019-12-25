package config;

import org.apache.velocity.VelocityContext;

import java.util.List;

public interface BeanListReader {
    public List getBeanList(VelocityContext context);
}
