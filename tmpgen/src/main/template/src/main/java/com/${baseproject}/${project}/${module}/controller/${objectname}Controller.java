package com.${baseproject}.${project}.${module}.controller;

import com.kedacom.kidp.base.web.controller.BaseCrudController;
import com.${baseproject}.${project}.${module}.entity.${objectname};
import com.${baseproject}.${project}.${module}.vo.${objectname}SearchVO;
import com.${baseproject}.${project}.${module}.vo.${objectname}VO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${module}/${objectnameLow}")
public class ${objectname}Controller extends BaseCrudController<${objectname}, ${objectname}VO, ${objectname}SearchVO> {
}
