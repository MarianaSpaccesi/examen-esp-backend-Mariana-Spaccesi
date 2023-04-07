package com.dh.catalog.repository;

import com.dh.catalog.client.MovieServiceClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepositoryMovie extends MongoRepository<MovieServiceClient.MovieDto, Long> {

    List<MovieServiceClient.MovieDto> findAllByGenre(String genre);

}
