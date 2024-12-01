package com.basicsourcecode.codeGenerator.sysCodeGenerator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SYS_DATA_FIELDS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DataFiled {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "FIELD_ID")
    private String fieldId;
    @Column(name = "Field_NAME_EN")
    private String nameFieldEn;
    @Column(name = "FILED_NAME_OTH_LAN")
    private String nameFieldOtherLang;
    @Column(name = "FIELD_TYPE")
    private String typeFiled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTITY_ID", nullable = false)
    private DataEntity dataEntity;

}
