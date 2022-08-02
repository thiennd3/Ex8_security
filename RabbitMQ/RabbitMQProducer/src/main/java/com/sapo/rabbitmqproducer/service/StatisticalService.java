package com.sapo.rabbitmqproducer.service;





import com.sapo.rabbitmqproducer.model.entity.Statistical;

import java.util.List;

public interface StatisticalService {


    Statistical getByStorageId(Integer storageId);

    List<Statistical> getStatisticals();
}
