package com.basicsourcecode.codeGenerator.xmlClass;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum TypeOfPrimaryKeyXmlGenerator {
//    @XmlEnumValue("Short")
//    Short,
//    @XmlEnumValue("String")
//    String ,
    @XmlEnumValue("Integer")
    Integer,
    @XmlEnumValue("Long")
    Long;

}
