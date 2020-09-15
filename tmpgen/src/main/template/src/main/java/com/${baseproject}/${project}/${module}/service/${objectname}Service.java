#set($objectnameTmp="${objectname}")
#set($objectnameFirstLow="${TmpGenUtil.getValFirstLow($objectnameTmp)}")
package com.${baseproject}.${project}.${module}.service;

import com.${baseproject}.${project}.base.service.BaseCrudService;
import com.${baseproject}.${project}.${module}.vo.${objectname}SearchVO;
import com.${baseproject}.${project}.${module}.vo.${objectname}VO;
import com.${baseproject}.${project}.${module}.entity.${objectname};
import org.springframework.data.domain.Page;

import java.util.List;

public interface ${objectname}Service extends BaseCrudService<${objectname}, ${objectname}VO> {
    List<${objectname}VO> retrieve(${objectname}SearchVO vo);

    Page retrievePage(${objectname}SearchVO searchVO);
}

