package com.${baseproject}.${project}.${module}.entity;

import com.kedacom.kidp.base.data.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="${tablename}")
public class ${objectname} extends BaseEntity {
    private static final long serialVersionUID = 0L;

#foreach( $field in ${fieldList})
#if(!$field.isIgnored())
#if($field.isPK())
    @Id
#end
    private $TmpGenUtil.getFieldJavaType($field.getType()) $field.getNameLow();
#end
#end
}