package com.basicsourcecode.codeGenerator.xmlClass.relation;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RelationsBasicXmlGenerator {
    @XmlTransient
    private String name ;
    @XmlTransient
    private String target ;
    @XmlTransient
    private String required ;
    @XmlTransient
    private String unique ;
    @XmlTransient
    private String otherLanguageName ;

}
