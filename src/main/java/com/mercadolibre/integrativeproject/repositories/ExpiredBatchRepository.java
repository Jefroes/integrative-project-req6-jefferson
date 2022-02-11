package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.ExpiredBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Repository do ExpiredBatchRepository
 *
 * @author Arthur Amorim
 *
 * */
@Repository
public interface ExpiredBatchRepository extends JpaRepository<ExpiredBatch, Long> {
    @Modifying
    @Query("select d from ExpiredBatch d where d.sector.id = ?1")
    List<ExpiredBatch> findExpiredBatchBySectorId(Long sectorId);
}
