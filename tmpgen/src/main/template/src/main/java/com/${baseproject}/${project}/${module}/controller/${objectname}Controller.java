package com.${baseproject}.${project}.${module}.controller;

import com.${baseproject}.ctsp.web.controller.message.ResponseMessage;
import com.${baseproject}.${project}.base.controller.BaseCommController;
import com.${baseproject}.${project}.${module}.entity.${objectname};
import com.${baseproject}.${project}.${module}.service.${objectname}Service;
import com.${baseproject}.${project}.${module}.vo.${objectname}SearchVO;
import com.${baseproject}.${project}.${module}.vo.${objectname}VO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${module}/${objectnameLow}")
public class ${objectname}Controller extends BaseCommController<${objectname}, ${objectname}VO, ${objectname}SearchVO> {
    @Autowired
    private ${objectname}Service ${objectnameLow}Service;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    @ApiOperation("Search Page")
    @PostMapping("/searchPage")
    public ResponseMessage searchPage(@RequestBody ${objectname}SearchVO searchVO) {
        Page page = ${objectnameLow}Service.retrievePage(searchVO);
        return ResponseMessage.ok(page);
    }
	
	@ApiOperation("Search")
    @PostMapping("/search")
    public ResponseMessage search(@RequestBody ${objectname}SearchVO searchVO) {
        List list = ${objectnameLow}Service.retrieve(searchVO);
        return ResponseMessage.ok(list);
    }
	
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

}
