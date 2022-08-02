package com.edu.sapo.ex9_kafka.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "statistical")
public class Statistical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "storage_id")
    private Integer storageId;

    @Column(name = "number")
    private Integer number;

    @Column(name = "create_at")
    private java.sql.Timestamp createAt;


}
