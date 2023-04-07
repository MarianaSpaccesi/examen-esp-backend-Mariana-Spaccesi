package com.dh.catalog.repository;

import com.dh.catalog.client.SerieServiceClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepositorySerie extends MongoRepository<SerieServiceClient.SerieDto,String> {

    List<SerieServiceClient.SerieDto> findAllByGenre(String genre);

}
