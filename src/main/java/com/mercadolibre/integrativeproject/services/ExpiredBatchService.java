package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.ExpiredBatch;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.enums.BatchStatus;
import com.mercadolibre.integrativeproject.repositories.BatchRepository;
import com.mercadolibre.integrativeproject.repositories.ExpiredBatchRepository;
import com.mercadolibre.integrativeproject.repositories.SectorRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IExpiredBatchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpiredBatchService implements IExpiredBatchService<Batch, Long> {


    BatchService batchService;

    SectorRepository sectorRepository;

    BatchRepository batchRepository;

    SectorService sectorService;

    ExpiredBatchRepository expiredBatchRepository;

    public ExpiredBatchService(BatchService batchService, SectorRepository sectorRepository, BatchRepository batchRepository, SectorService sectorService, ExpiredBatchRepository expiredBatchRepository) {
        this.batchService = batchService;
        this.sectorRepository = sectorRepository;
        this.batchRepository = batchRepository;
        this.sectorService = sectorService;
        this.expiredBatchRepository = expiredBatchRepository;
    }

    public ExpiredBatch create(Long sectorId, List<Batch> batchesList) {
        List<Batch> batchListFiltered = batchService.filterBacthsByDueDate(5, batchesList);
        Sector sector = sectorService.getById(sectorId);
        List<Batch> allBatchesExpired = batchListFiltered.stream().map(batch -> {
            Batch batchById = batchService.getById(batch.getId());
            batchById.setStatus(BatchStatus.EXPIRED);
            return batchById;
        }).collect(Collectors.toList());
        batchRepository.saveAll(allBatchesExpired);
        ExpiredBatch expiredBatches = ExpiredBatch.builder().sector(sector).expiredBatchList(allBatchesExpired).build();
        return expiredBatchRepository.save(expiredBatches);
    }

    //@Override
    public List<Batch> getByIdSectorAllBatchesExpired(Long sectorId) {
        return batchService.getBatchesByDueDate(5, sectorId);
    }


    public List<ExpiredBatch> findExpiredBatchBySectorId(Long sectorId) {
        return expiredBatchRepository.findExpiredBatchBySectorId(sectorId);
    }

    //@Override
    public List<ExpiredBatch> getAll() {
        return expiredBatchRepository.findAll();
    }
}
