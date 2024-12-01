package com.basicsourcecode.codeGenerator.xmlClass.property;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class PropertyXmlGenerator {

    @XmlElement(name = "enum", namespace = "https://www.basicSourceCodeXSD.com")
    private EnumClassXmlGenerator enumClassXmlGeneratorList;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "type")
    private TypePropertyXmlGenerator type;
    @XmlAttribute(name = "required")
    private Boolean required;
    @XmlAttribute(name = "unique")
    private Boolean unique;
    @XmlAttribute(name = "otherLanguageName")
    private String otherLanguageName;
    @XmlAttribute(name = "columnName")
    private String columnName;
    @XmlAttribute(name = "length")
    private Integer length;
    @XmlAttribute(name = "dateTime")
    private Boolean dateTime;
    @XmlAttribute(name = "time")
    private Boolean time;
    @XmlAttribute(name = "min")
    private Integer min;
    @XmlAttribute(name = "max")
    private Integer max;
    @XmlAttribute(name = "fieldSaveToDB")
    private Boolean fieldSaveToDB;

}
