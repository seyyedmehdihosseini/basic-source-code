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
public class OneToManyRelationXmlGenerator extends RelationsBasicXmlGenerator{
    @XmlAttribute(name = "inverseColumnName")
    private String inverseColumnName;
    @XmlAttribute(name = "inverseForeignKey")
    private String inverseForeignKey;
    @XmlAttribute(name = "withSet")
    private String withSet;
    @XmlAttribute(name = "strongToWeak")
    private String strongToWeak;

}
