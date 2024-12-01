package com.basicsourcecode.codeGenerator.xmlClass.relation;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.AccessType;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class RelationXmlGenerator {
    @XmlElementWrapper(name = "relations", namespace = "https://www.basicSourceCodeXSD.com")
    @XmlElement(name = "oneToOne", namespace = "https://www.basicSourceCodeXSD.com")
    private List<OneToOneRelationXmlGenerator> oneToOne;
    @XmlElementWrapper(name = "relations", namespace = "https://www.basicSourceCodeXSD.com")
    @XmlElement(name = "oneToMany", namespace = "https://www.basicSourceCodeXSD.com")
    private List<OneToManyRelationXmlGenerator> oneToMany;
    @XmlElementWrapper(name = "relations", namespace = "https://www.basicSourceCodeXSD.com")
    @XmlElement(name = "manyToOne", namespace = "https://www.basicSourceCodeXSD.com")
    private List<ManyToOneRelationXmlGenerator> manyToOne;
    @XmlElementWrapper(name = "relations", namespace = "https://www.basicSourceCodeXSD.com")
    @XmlElement(name = "manyToMany", namespace = "https://www.basicSourceCodeXSD.com")
    private List<ManyToManyRelationXmlGenerator> manyToMany;

}
