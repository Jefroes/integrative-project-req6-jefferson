package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.ExpiredBatch;
import com.mercadolibre.integrativeproject.entities.Responsible;
import com.mercadolibre.integrativeproject.entities.Sector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Validação de campos e criação de DTOs
 *
 * @author Jefferson Froes
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpiredBatchDTO {

    private Long id;

    @Valid
    @NotNull(message = "Sector is not null")
    private Sector sector;

    private Responsible responsible;

    @NotNull(message = "List of batches needs to be send.")
    private List<Batch> expiredBatchList = new ArrayList<>();

    private Timestamp date;


    /**
     * Método utilizado para conversão do objetoDTO para objeto.
     *
     * @author Jefferson Froes
     * @param expiredBatchDTO - Objeto a ser convertido.
     * @return objeto convertido.
     *
     */
    public static ExpiredBatch convert(ExpiredBatchDTO expiredBatchDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(expiredBatchDTO, ExpiredBatch.class);
    }

    /**
     * Método utilizado para conversão do objeto para objetoDTO.
     *
     * @author Jefferson Froes
     * @param expiredBatch - Objeto a ser convertido.
     * @return objeto convertido.
     *
     */
    public static ExpiredBatchDTO convert(ExpiredBatch expiredBatch) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(expiredBatch, ExpiredBatchDTO.class);
    }
}
