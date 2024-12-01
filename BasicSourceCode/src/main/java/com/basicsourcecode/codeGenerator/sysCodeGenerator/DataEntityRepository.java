package com.basicsourcecode.codeGenerator.sysCodeGenerator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataEntityRepository extends JpaRepository<DataEntity, String> {
    DataEntity findByEntityNameEn(String entityNameEn);
}
