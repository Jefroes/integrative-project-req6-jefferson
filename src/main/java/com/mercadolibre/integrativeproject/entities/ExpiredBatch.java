package com.mercadolibre.integrativeproject.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class ExpiredBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Batch> batchListExpired;

    @ManyToOne
    private Sector sector;

    @Column
    private Timestamp data;

}
