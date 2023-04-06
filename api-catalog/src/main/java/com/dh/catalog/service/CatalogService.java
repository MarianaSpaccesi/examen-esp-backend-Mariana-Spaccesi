package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {
    private MovieServiceClient movieServiceClient;
    private SerieServiceClient serieServiceClient;

    public CatalogService(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
    }


    @CircuitBreaker(name = "getMovieByGenre", fallbackMethod = "getMovieFallback")
    @Retry(name = "getMovieByGenre")
    public List<MovieServiceClient.MovieDto> getMovieByGenre (String genre) {
        return movieServiceClient.getMovieByGenre(genre);
    }

    @CircuitBreaker(name = "getSerieByGenre", fallbackMethod = "getSerieFallback")
    @Retry(name = "getSerieByGenre")
    public List<SerieServiceClient.SerieDto> getSerieByGenre (String genre) {
        return serieServiceClient.getSerieByGenre(genre);
    }


    private List<MovieServiceClient.MovieDto> getMovieFallback (String genre, Throwable throwable) throws Exception {
        List<MovieServiceClient.MovieDto> movieList = new ArrayList<>();
        return movieList;
    }

    private List<SerieServiceClient.SerieDto> getSerieFallback (String genre, Throwable throwable) throws Exception {
        List<SerieServiceClient.SerieDto> serieList = new ArrayList<>();
        return serieList;
    }


}
