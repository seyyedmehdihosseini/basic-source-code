package com.basicsourcecode.codeGenerator.sysCodeGenerator;

import com.basicsourcecode.codeGenerator.xmlClass.ModelXml;

public interface DataEntityService {
    DataEntity registryDataEntityToDB(ModelXml modelXml);
    DataEntity findByEntityName(String nameEntity);

}
