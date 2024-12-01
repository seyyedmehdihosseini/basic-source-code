package com.basicsourcecode.codeGenerator.sysCodeGenerator;

import com.basicsourcecode.codeGenerator.xmlClass.property.PropertyXmlGenerator;

import java.util.List;

public interface DataFieldService {
    DataFiled registryDataFieldToDB(DataFiled dataFiled);

    List<DataFiled> registryListDataFieldToDB(List<PropertyXmlGenerator> propertyXmlGeneratorList, String entityName);

    DataFiled findByFieldNameEnAndEntityName(String fieldName, String entityName);
}
