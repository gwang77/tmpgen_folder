package ${baseproject}.${project}.${submodule}.service;

import org.springframework.stereotype.Component;
import ${baseproject}.${project}.common.base.service.BaseService;
import ${baseproject}.${project}.${submodule}.mapper.${objectname}Mapper;

import javax.annotation.Resource;

@Component
public class ${objectname}Service extends BaseService {
    @Resource(name = "${baseproject}.${project}.${submodule}.mapper.${objectname}Mapper")
    public void setMapper(${objectname}Mapper mapper) {
        super.setMapper(mapper);
    }
}
