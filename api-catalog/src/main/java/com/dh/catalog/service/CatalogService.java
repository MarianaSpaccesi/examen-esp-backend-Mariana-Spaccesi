package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.event.MovieCreatedConsumerEvent;
import com.dh.catalog.repository.CatalogRepositoryMovie;
import com.dh.catalog.repository.CatalogRepositorySerie;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CatalogService {
    private MovieServiceClient movieServiceClient;
    private SerieServiceClient serieServiceClient;
    private CatalogRepositoryMovie catalogRepositoryMovie;
    private CatalogRepositorySerie catalogRepositorySerie;


    public CatalogService(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient, CatalogRepositoryMovie catalogRepositoryMovie, CatalogRepositorySerie catalogRepositorySerie) {
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
        this.catalogRepositoryMovie = catalogRepositoryMovie;
        this.catalogRepositorySerie = catalogRepositorySerie;
    }

    @CircuitBreaker(name = "getMovieByGenre", fallbackMethod = "findAllMoviesOffline")
    @Retry(name = "getMovieByGenre")
    public List<MovieServiceClient.MovieDto> getMovieByGenre(String genre) {
        return movieServiceClient.getMovieByGenre(genre);
    }

    @CircuitBreaker(name = "getSerieByGenre", fallbackMethod = "findAllSeriesOffline")
    @Retry(name = "getSerieByGenre")
    public List<SerieServiceClient.SerieDto> getSerieByGenre(String genre) {
        return serieServiceClient.getSerieByGenre(genre);
    }


    public List<MovieServiceClient.MovieDto> getMovieByGenreOffline(String genre) {
        return catalogRepositoryMovie.findAllByGenre(genre);
    }

    public List<SerieServiceClient.SerieDto> findAllSeriesOffline(String genre) {
        return catalogRepositorySerie.findAllByGenre(genre);
    }

    public List<MovieServiceClient.MovieDto> findAllMoviesOffline(String genre, Throwable t) throws Exception {
        return catalogRepositoryMovie.findAllByGenre(genre);
    }

    public List<SerieServiceClient.SerieDto> findAllSeriesOffline(String genre, Throwable t) throws Exception {
        return catalogRepositorySerie.findAllByGenre(genre);
    }


    public void saveMovie(MovieCreatedConsumerEvent.Data data) {
        try {
            MovieServiceClient.MovieDto movie = new MovieServiceClient.MovieDto();
            movie.setId(Long.parseLong(data.getId()));
            movie.setGenre(data.getGenre());
            movie.setName(data.getName());
            movie.setUrlStream(data.getUrlStream());
            catalogRepositoryMovie.save(movie);
        } catch (Exception e) {
            log.error(e.getMessage());
        }


    }
}