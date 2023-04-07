package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.repository.CatalogRepositoryMovie;
import com.dh.catalog.repository.CatalogRepositorySerie;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {
    private MovieServiceClient movieServiceClient;
    private SerieServiceClient serieServiceClient;
    private CatalogRepositoryMovie catalogRepositoryMovie;
    private CatalogRepositorySerie catalogRepositorySerie;


    public String createMovie (MovieServiceClient.MovieDto movieDto){
        var movieSaved = catalogRepositoryMovie.save(movieDto);
        return movieSaved.getId().toString();
    }


    public CatalogService(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient, CatalogRepositoryMovie catalogRepositoryMovie, CatalogRepositorySerie catalogRepositorySerie) {
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
        this.catalogRepositoryMovie = catalogRepositoryMovie;
        this.catalogRepositorySerie = catalogRepositorySerie;
    }

    @CircuitBreaker(name = "getMovieByGenre", fallbackMethod = "findAllMoviesOffline")
    @Retry(name = "getMovieByGenre")
    public List<MovieServiceClient.MovieDto> getMovieByGenre (String genre) {
        return movieServiceClient.getMovieByGenre(genre);
    }

    @CircuitBreaker(name = "getSerieByGenre", fallbackMethod = "findAllSeriesOffline")
    @Retry(name = "getSerieByGenre")
    public List<SerieServiceClient.SerieDto> getSerieByGenre (String genre) {
        return serieServiceClient.getSerieByGenre(genre);
    }


    /*private List<MovieServiceClient.MovieDto> getMovieFallback (String genre)  {
        return catalogRepositoryMovie.findAllByGenre(genre);
    }

    private List<SerieServiceClient.SerieDto> getSerieFallback (String genre)  {
        return catalogRepositorySerie.findAllByGenre(genre);
    }*/

    public List<MovieServiceClient.MovieDto> findAllMoviesOffline (String genre ) {
        return catalogRepositoryMovie.findAllByGenre(genre);
    }

    public List<SerieServiceClient.SerieDto> findAllSeriesOffline (String genre ) {
        return catalogRepositorySerie.findAllByGenre(genre);
    }

}
