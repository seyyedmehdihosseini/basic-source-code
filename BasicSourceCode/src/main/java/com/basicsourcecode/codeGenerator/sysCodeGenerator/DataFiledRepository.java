package com.basicsourcecode.codeGenerator.sysCodeGenerator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataFiledRepository extends JpaRepository<DataFiled, String> {
    DataFiled findByNameFieldEnAndDataEntity_EntityNameEn(String fieldName, String entityName);

}
