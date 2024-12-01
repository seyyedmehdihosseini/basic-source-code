package com.basicsourcecode.codeGenerator.sysCodeGenerator;

import com.basicsourcecode.codeGenerator.xmlClass.property.PropertyXmlGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFieldServiceImpl implements DataFieldService {

    private DataFiledRepository dataFiledRepository;
    private DataEntityRepository dataEntityRepository;

    @Autowired
    public DataFieldServiceImpl(DataEntityRepository dataEntityRepository, DataFiledRepository dataFiledRepository) {
        this.dataEntityRepository = dataEntityRepository;
        this.dataFiledRepository = dataFiledRepository;
    }


    @Override
    public DataFiled registryDataFieldToDB(DataFiled dataFiled) {

        DataFiled byFieldNameEnAndEntityName = findByFieldNameEnAndEntityName(dataFiled.getNameFieldEn(), dataFiled.getDataEntity().getEntityNameEn());
        if (byFieldNameEnAndEntityName != null)
            return byFieldNameEnAndEntityName;

        return dataFiledRepository.save(dataFiled);
    }

    @Override
    public List<DataFiled> registryListDataFieldToDB(List<PropertyXmlGenerator> propertyXmlGeneratorList, String entityName) {
        DataEntity dataEntity = dataEntityRepository.findByEntityNameEn(entityName);
        if (dataEntity == null)
            throw new RuntimeException("Entity by name " + entityName + " is not exist in database ");

        List<DataFiled> dataFiledList = new ArrayList<>();

        propertyXmlGeneratorList = propertyXmlGeneratorList.stream().filter(PropertyXmlGenerator::getFieldSaveToDB).collect(Collectors.toList());

        if (propertyXmlGeneratorList.isEmpty())
            return null;

        for (PropertyXmlGenerator propertyXmlGenerator : propertyXmlGeneratorList) {
            DataFiled dataFiled = new DataFiled();
            dataFiled.setNameFieldEn(propertyXmlGenerator.getName());
            dataFiled.setNameFieldOtherLang(propertyXmlGenerator.getOtherLanguageName());
            dataFiled.setTypeFiled(propertyXmlGenerator.getType().name());
            dataFiled.setDataEntity(dataEntity);
            dataFiledList.add(registryDataFieldToDB(dataFiled));
        }

        return dataFiledList;
    }

    @Override
    public DataFiled findByFieldNameEnAndEntityName(String fieldName, String entityName) {
        return dataFiledRepository.findByNameFieldEnAndDataEntity_EntityNameEn(fieldName, entityName);
    }

}
