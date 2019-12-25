#set($objectnameTmp="${objectname}")
#set($objectnameFirstLow="${TmpGenUtil.getValFirstLow($objectnameTmp)}")
package com.${baseproject}.${project}.${module}.service;

import com.${baseproject}.${project}.base.service.BaseCrudService;
import com.${baseproject}.${project}.${module}.vo.${objectname}VO;
import com.${baseproject}.${project}.${module}.entity.${objectname};

public interface ${objectname}Service extends BaseCrudService<${objectname}, ${objectname}VO>{
}

