package com.basicsourcecode.codeGenerator.xmlClass.property;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class ItemEnumClassXmlGenerator {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "title")
    private String title;
    @XmlAttribute(name = "code")
    private Integer code;
}
