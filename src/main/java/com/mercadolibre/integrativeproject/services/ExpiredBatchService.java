package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.ExpiredBatch;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.repositories.BatchRepository;
import com.mercadolibre.integrativeproject.repositories.ExpiredBatchRepository;
import com.mercadolibre.integrativeproject.repositories.SectorRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IExpiredBatchService;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public ExpiredBatch create(Long sectorId, List<Batch> expiredBatchList) {
        Sector sector = sectorRepository.getById(sectorId);
        ExpiredBatch expiredBatch = new ExpiredBatch();
        expiredBatch.getSector().setId(sectorId);
        expiredBatch.setExpiredBatchList(expiredBatchList);
        return expiredBatchRepository.save(expiredBatch);
    }

    @Override
    public List<Batch> getByIdSectorAllBatchesExpired(Long sectorId) {
        Sector sector = sectorRepository.getById(sectorId);
        return batchService.getBatchesByDueDate(5, sector.getResponsible().getId());
    }

    @Override
    public List<Batch> getAll() {
        return batchRepository.findAll();
    }
}
