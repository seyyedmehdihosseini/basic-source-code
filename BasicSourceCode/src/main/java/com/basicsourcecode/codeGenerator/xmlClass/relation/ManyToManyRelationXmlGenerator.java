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
public class ManyToManyRelationXmlGenerator extends RelationsBasicXmlGenerator {
    @XmlAttribute(name = "columnName")
    private String columnName;
    @XmlAttribute(name = "inverseColumnName")
    private String inverseColumnName;
    @XmlAttribute(name = "foreignKey")
    private String foreignKey;
    @XmlAttribute(name = "inverseForeignKey")
    private String inverseForeignKey;
    @XmlAttribute(name = "joinTable")
    private String joinTable;
    @XmlAttribute(name = "withSet")
    private String withSet;
}
