package com.basicsourcecode.codeGenerator.xmlClass.property;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum TypePropertyXmlGenerator {
    @XmlEnumValue("Boolean")
    Boolean,
    @XmlEnumValue("Byte")
    Byte,
    @XmlEnumValue("Short")
    Short,
    @XmlEnumValue("Integer")
    Integer,
    @XmlEnumValue("Long")
    Long,
    @XmlEnumValue("Double")
    Double,
    @XmlEnumValue("Character")
    Character,
    @XmlEnumValue("String")
    String,
    @XmlEnumValue("Date")
    Date,
    @XmlEnumValue("Blob")
    Blob,
    @XmlEnumValue("Clob")
    Clob
}
