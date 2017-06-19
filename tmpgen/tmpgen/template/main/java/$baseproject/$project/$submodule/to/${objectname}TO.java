package ${baseproject}.${project}.${submodule}.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ${baseproject}.${project}.common.base.to.BaseTO;
import ${baseproject}.${project}.common.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

public class ${objectname}TO extends BaseTO {
#foreach( $field in $fieldList )
#if(!$field.isIgnored())
#if($TmpGenUtil.isByteArr($field.getType()) || $TmpGenUtil.isString($field.getType()))
    private $TmpGenUtil.getFieldJavaType($field.getType()) $field.getNameFirstLow();
#else
    private String $field.getNameFirstLow()_s;
#end
#end
#end

#foreach( $field in $fieldList )
#if(!$field.isIgnored())
#if($TmpGenUtil.isByteArr($field.getType()) || $TmpGenUtil.isString($field.getType()))
    public $TmpGenUtil.getFieldJavaType($field.getType()) get$field.getNameFirstUp()() {
        return $field.getNameFirstLow();
    }

    public void set$field.getNameFirstUp()($TmpGenUtil.getFieldJavaType($field.getType()) $field.getNameFirstLow()) {
        this.$field.getNameFirstLow() = $field.getNameFirstLow();
    }
#else
#if($TmpGenUtil.isDate($field.getType()))
    @JsonIgnore
    public Date get$field.getNameFirstUp()() {
        if ($field.getNameFirstLow()_s == null || "".equals($field.getNameFirstLow()_s)) {
            return null;
        }
        return DateUtil.parseDate($field.getNameFirstLow()_s);
    }

    public void set$field.getNameFirstUp()(Date $field.getNameFirstLow()) {
        if ($field.getNameFirstLow() == null) {
            $field.getNameFirstLow()_s = null;
            return;
        }
        $field.getNameFirstLow()_s = DateUtil.formatDate($field.getNameFirstLow());
    }
#end
#if($TmpGenUtil.isInt($field.getType()) || $TmpGenUtil.isDouble($field.getType()))
    @JsonIgnore
    public $TmpGenUtil.getFieldJavaType($field.getType()) get$field.getNameFirstUp()() {
        if ($field.getNameFirstLow()_s == null || "".equals($field.getNameFirstLow()_s)) {
            return null;
        }
        return new $TmpGenUtil.getFieldJavaType($field.getType())($field.getNameFirstLow()_s);
    }

    public void set$field.getNameFirstUp()($TmpGenUtil.getFieldJavaType($field.getType()) $field.getNameFirstLow()) {
        if ($field.getNameFirstLow() == null) {
            $field.getNameFirstLow()_s = null;
            return;
        }
        $field.getNameFirstLow()_s = String.valueOf($field.getNameFirstLow());
    }
#end

    public String get$field.getNameFirstUp()_s() {
        return $field.getNameFirstLow()_s;
    }

    public void set$field.getNameFirstUp()_s(String $field.getNameFirstLow()_s) {
        this.$field.getNameFirstLow()_s = $field.getNameFirstLow()_s;
    }
#end

#end
#end
}


