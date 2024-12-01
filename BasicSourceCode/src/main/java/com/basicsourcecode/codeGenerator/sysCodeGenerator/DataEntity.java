package com.basicsourcecode.codeGenerator.sysCodeGenerator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SYS_DATA_ENTITY")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ENTITY_ID")
    private String entityId;

    @Column(name = "ENTITY_NAME_EN")
    private String entityNameEn;
    @Column(name = "ENTITY_NAME_OTH_LANG")
    private String entityNameOtherLang;

}
