package com.mercadolibre.integrativeproject.services.interfaces;

import java.util.List;

/** Interface para ExpiredBatchService
 *
 * @author Jefferson Froes
 *
 * */
public interface IExpiredBatchService<T, ID> {

    List<T> getByIdSectorAllBatchesExpired(ID id);

    List<T> getAll();
}
