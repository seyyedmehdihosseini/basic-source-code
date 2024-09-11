package com.basicsourcecode.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@MappedSuperclass
public abstract class BasicAuditableEntity {

    private String createBy;

    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;

    @Version
    private Integer version;

}
