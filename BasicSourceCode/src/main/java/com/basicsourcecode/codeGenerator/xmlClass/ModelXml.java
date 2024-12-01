package com.basicsourcecode.codeGenerator.xmlClass;

import com.basicsourcecode.codeGenerator.xmlClass.property.PropertyXmlGenerator;
import com.basicsourcecode.codeGenerator.xmlClass.relation.RelationXmlGenerator;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@XmlRootElement(name = "model" , namespace = "https://www.basicSourceCodeXSD.com")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class ModelXml {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "otherLanguageName")
    private String otherLanguageName;
    @XmlAttribute(name = "typeOfPrimaryKey")
    private TypeOfPrimaryKeyXmlGenerator typeOfPrimaryKey;
    @XmlAttribute(name = "tableName")
    private String tableName;
    @XmlAttribute(name = "overwrite")
    private Boolean overwrite;
    @XmlAttribute(name = "entitySaveToDB")
    private Boolean entitySaveToDB;

    @XmlElementWrapper(name = "properties" , namespace = "https://www.basicSourceCodeXSD.com")
    @XmlElement(name = "property" , namespace = "https://www.basicSourceCodeXSD.com")
    private List<PropertyXmlGenerator> propertyXmlGeneratorList;
    @XmlElement(name = "relations")
    private RelationXmlGenerator relations;

}
