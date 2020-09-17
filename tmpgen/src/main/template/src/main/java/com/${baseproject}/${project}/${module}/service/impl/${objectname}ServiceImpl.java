#set($objectnameTmp="${objectname}")
#set($objectnameFirstLow="${TmpGenUtil.getValFirstLow($objectnameTmp)}")

package com.${baseproject}.${project}.${module}.service.impl;

import com.${baseproject}.kidp.base.data.common.jpql.JpqlQuerySupport;
import com.${baseproject}.${project}.base.service.impl.BaseCrudServiceImpl;
import com.${baseproject}.${project}.${module}.vo.${objectname}SearchVO;
import com.${baseproject}.${project}.${module}.vo.${objectname}VO;
import com.${baseproject}.${project}.${module}.entity.${objectname};
import com.${baseproject}.${project}.${module}.service.${objectname}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ${objectname}ServiceImpl extends BaseCrudServiceImpl<${objectname}, ${objectname}VO> implements ${objectname}Service {
    @Autowired
    private JpqlQuerySupport jpqlQuerySupport;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // search based on jpql
    public List<${objectname}VO> retrieve(${objectname}SearchVO vo) {
        List<${objectname}VO> list = jpqlQuerySupport.findAll("${objectnameFirstLow}.search", vo);
        return list;
    }

    public Page retrievePage(${objectname}SearchVO searchVO) {
        return jpqlQuerySupport.findPage("${objectnameFirstLow}.search", searchVO, searchVO.toPageable());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
}
