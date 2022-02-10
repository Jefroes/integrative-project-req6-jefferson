package com.mercadolibre.integrativeproject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/** Entidade ExpiredBatchRepository
 *
 * @author Jefferson Froes
 *
 **/
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

    @Column
    @OneToMany
    @JsonBackReference
    private List<Batch> expiredBatchList = new ArrayList<>();

    @ManyToOne
    private Sector sector;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp date;
}
