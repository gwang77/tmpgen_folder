package com.${baseproject}.${project}.${module}.dao;

import com.${baseproject}.kidp.base.data.common.repository.BaseJpaRepository;
import com.${baseproject}.${project}.${module}.entity.${objectname};
import org.springframework.stereotype.Repository;

@Repository
public interface ${objectname}Repository extends BaseJpaRepository<${objectname}> {
}
