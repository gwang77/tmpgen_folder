package com.${baseproject}.${project}.${module}.vo;

import com.${baseproject}.${project}.base.vo.BaseVO;
import com.${baseproject}.${project}.${module}.entity.${objectname};
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ${objectname}VO extends BaseVO<${objectname}> {
    private static final long serialVersionUID = 0L;

#foreach( $field in ${fieldList})
#if(!$field.isIgnored())
    private $TmpGenUtil.getFieldJavaType($field.getType()) $field.getNameLow();
#end
#end
}