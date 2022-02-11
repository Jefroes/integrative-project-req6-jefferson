package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.ExpiredBatch;
import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.enums.BatchStatus;
import com.mercadolibre.integrativeproject.enums.StorageType;
import com.mercadolibre.integrativeproject.repositories.BatchRepository;
import com.mercadolibre.integrativeproject.repositories.ExpiredBatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpiredBatchServiceTest {

    @Mock
    ExpiredBatchRepository mockExpiredBatchRepository;

    @Mock
    BatchRepository mockBatchRepository;

    @Mock
    BatchService batchService;

    @Mock
    SectorService sectorService;

    @InjectMocks
    ExpiredBatchService expiredBatchService;

    @Test
    void create() {

        // Arrange
        Product product1 = Product.builder().id(1L).build();
        //Product product2 = Product.builder().id(2L).build();
        Batch batches1 = getBatchCreated(product1,1L);
        //Batch batches2 = getBatchCreated(product2,1L);
        Sector sector = Sector.builder().id(1L).build();

        List<Batch> batchArrayList = new ArrayList<>(Arrays.asList(batches1));

        ExpiredBatch expiredBatches = ExpiredBatch.builder().sector(sector).expiredBatchList(batchArrayList).build();
        ExpiredBatch expiredBatches1 = null;

        when(batchService.filterBacthsByDueDate(5,batchArrayList)).thenReturn(batchArrayList);
        when(batchService.getById(1L)).thenReturn(batches1);
        when(sectorService.getById(sector.getId())).thenReturn(sector);
        when(mockBatchRepository.saveAll(batchArrayList)).thenReturn(batchArrayList);
        lenient().when(mockExpiredBatchRepository.save(expiredBatches)).thenReturn(expiredBatches);

        // Act
        ExpiredBatch expiredBatchCreate = expiredBatchService.create(sector.getId(), batchArrayList);

        // Assert
        assertEquals(expiredBatches1, expiredBatchCreate);
    }


    @Test
    void getByIdSectorAllBatchesExpired() {
    }

    @Test
    void findExpiredBatchBySectorId() {
    }

    @Test
    void getAll() {
    }

    // Arrange
    private BatchService getBatchServiceMock(BatchRepository batchRepository) {
        return new BatchService(batchRepository, new SectorService());
    }

    private Product getProduct(String name) {
        Product product = new Product();
        product.setId(1L);
        product.setName(name);
        product.setVolumn(10.0);
        product.setCategory(StorageType.FF);
        return product;
    }

    private ArrayList<Batch> getBatchWithoutId(Product product) {
        Batch batch1BeforeCreate = getBatchAfterCreate(product);

        Batch batch2BeforeCreate = getBatchAfterCreate(product);

        ArrayList<Batch> batchesBefore = new ArrayList<>();
        batchesBefore.add(0, batch1BeforeCreate);
        batchesBefore.add(1, batch2BeforeCreate);
        return batchesBefore;
    }

    private Batch getBatchAfterCreate(Product product) {
        Batch batch1BeforeCreate = new Batch();
        batch1BeforeCreate.setProduct(product);
        batch1BeforeCreate.setInitialQuantity(100L);
        batch1BeforeCreate.setQuantity(100L);
        batch1BeforeCreate.setCurrentTemperature(3.1);
        batch1BeforeCreate.setMinimumTemperature(4.0);
        batch1BeforeCreate.setBrand("Marca teste");
        batch1BeforeCreate.setExpirationDate(new Timestamp(1643727600000L));
        batch1BeforeCreate.setFabricationDate(new Timestamp(1609513200000L));
        batch1BeforeCreate.setPricePerUnit(new BigDecimal("10.2"));
        return batch1BeforeCreate;
    }

    private ArrayList<Batch> getBatchesWitchId(Product product) {
        Batch batch2AfterCreate = getBatchCreated(product, 2L);
        Batch batch1AfterCreate = getBatchCreated(product, 1L);
        ArrayList<Batch> batchesAfter = new ArrayList<>();
        batchesAfter.add(0, batch1AfterCreate);
        batchesAfter.add(1, batch2AfterCreate);
        return batchesAfter;
    }

    private Batch getBatchCreated(Product product, long l) {
        Batch resultGetById = new Batch();
        resultGetById.setId(l);
        resultGetById.setProduct(product);
        resultGetById.setInitialQuantity(100L);
        resultGetById.setQuantity(100L);
        resultGetById.setCurrentTemperature(3.1);
        resultGetById.setMinimumTemperature(4.0);
        resultGetById.setBrand("Marca teste");
        resultGetById.setExpirationDate(new Timestamp(1643727600000L));
        resultGetById.setFabricationDate(new Timestamp(1609513200000L));
        resultGetById.setPricePerUnit(new BigDecimal("10.2"));
        return resultGetById;
    }

    private BatchRepository getBatchRepositoryMock() {
        return Mockito.mock(BatchRepository.class);
    }

}

