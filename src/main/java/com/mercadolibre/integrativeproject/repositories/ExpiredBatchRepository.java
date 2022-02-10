package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.ExpiredBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repository do ExpiredBatchRepository
 *
 * @author Arthur Amorim
 *
 * */
@Repository
public interface ExpiredBatchRepository extends JpaRepository<ExpiredBatch, Long> {
}
