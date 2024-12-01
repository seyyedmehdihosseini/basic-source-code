package com.basicsourcecode.codeGenerator.xmlClass.relation;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ManyToOneRelationXmlGenerator extends RelationsBasicXmlGenerator {
    @XmlAttribute(name = "columnName")
    private String columnName;
    @XmlAttribute(name = "foreignKey")
    private String foreignKey;
}
