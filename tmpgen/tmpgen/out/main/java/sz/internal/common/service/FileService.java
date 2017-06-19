package sz.internal.common.service;

import org.springframework.stereotype.Component;
import sz.internal.common.base.service.BaseService;
import sz.internal.common.mapper.FileMapper;

import javax.annotation.Resource;

@Component
public class FileService extends BaseService {
    @Resource(name = "sz.internal.common.mapper.FileMapper")
    public void setMapper(FileMapper mapper) {
        super.setMapper(mapper);
    }
}
