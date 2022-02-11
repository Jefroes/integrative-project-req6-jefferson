package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.BatchDTO;
import com.mercadolibre.integrativeproject.dtos.CreateCustomerDTO;
import com.mercadolibre.integrativeproject.dtos.ExpiredBatchDTO;
import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.services.ExpiredBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/** Controller de registro de responsible.
 *
 * @author Jefferson Froes
 *
 * */
@RestController
@RequestMapping("/expired-batch")
public class ExpiredBatchController {

    @Autowired
    ExpiredBatchService expiredBatchService;

    /**
     * Método usado para criar uma lista de produtos expirados.
     *
     * @param sectorId - recebe o id do setor e uma lista de lotes vencidos.
     * @return retorna um responsible convertido em responsibleDTO
     * @author Jefferson Freos
     */
    @PostMapping("/{sectorId}")
    public ResponseEntity<ExpiredBatchDTO> create(@PathVariable Long sectorId, @RequestBody List<BatchDTO> batchDTO) {
        List<Batch> expiredBatches = batchDTO.stream().map(BatchDTO::coverte).collect(Collectors.toList());
        expiredBatchService.create(sectorId, expiredBatches);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/{sectorId}")
    public ResponseEntity<List<BatchDTO>> getByIdSectorAllBatchesExpired(@PathVariable Long sectorId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                expiredBatchService.getByIdSectorAllBatchesExpired(sectorId)
                        .stream().map(BatchDTO::convert).collect(Collectors.toList()));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExpiredBatchDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                expiredBatchService.getAll().stream().map(ExpiredBatchDTO::convert).collect(Collectors.toList()));
    }

    @GetMapping("/sector/{sectorId}")
    public ResponseEntity<List<ExpiredBatchDTO>> getAllBatchesExpiredBySector(@PathVariable Long sectorId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                expiredBatchService.findExpiredBatchBySectorId(sectorId).stream().map(ExpiredBatchDTO::convert).collect(Collectors.toList()));

    }
}