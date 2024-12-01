package com.basicsourcecode.codeGenerator.sysCodeGenerator;

import com.basicsourcecode.codeGenerator.xmlClass.ModelXml;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataEntityServiceImpl implements DataEntityService {
    private final DataEntityRepository dataEntityRepository;

    @Override
    public DataEntity registryDataEntityToDB(ModelXml modelXml) {
        DataEntity dataEntity = new DataEntity();
        dataEntity.setEntityNameEn(modelXml.getName());
        dataEntity.setEntityNameOtherLang(modelXml.getOtherLanguageName());
        return dataEntityRepository.save(dataEntity);
    }

    @Override
    public DataEntity findByEntityName(String entityName) {
        return dataEntityRepository.findByEntityNameEn(entityName);
    }
}
