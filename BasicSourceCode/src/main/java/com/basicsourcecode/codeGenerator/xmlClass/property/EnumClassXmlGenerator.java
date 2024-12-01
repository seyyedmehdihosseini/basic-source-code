package com.basicsourcecode.codeGenerator.xmlClass.property;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class EnumClassXmlGenerator {

    @XmlElementWrapper(name = "enum", namespace = "https://www.basicSourceCodeXSD.com")
    @XmlElement(name = "item", namespace = "https://www.basicSourceCodeXSD.com")
    private List<ItemEnumClassXmlGenerator> item;
    @XmlAttribute(name = "includeCodeInTitle")
    private Boolean includeCodeInTitle;
}
