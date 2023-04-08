package com.dh.movie.service;


import com.dh.movie.event.MovieCreatedProducerEvent;
import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {


    private final MovieRepository movieRepository;

    private MovieCreatedProducerEvent movieCreated;

    public MovieService(MovieRepository movieRepository , MovieCreatedProducerEvent movieCreated) {
        this.movieRepository = movieRepository;
        this.movieCreated = movieCreated;
    }

    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public Movie save(Movie movie) {
        Movie newMovie = movieRepository.save(movie);
        MovieCreatedProducerEvent.Data data = new MovieCreatedProducerEvent.Data();
        data.setId(String.valueOf(newMovie.getId()));
        data.setName(newMovie.getName());
        data.setGenre(newMovie.getGenre());
        data.setUrlStream(newMovie.getUrlStream());
        movieCreated.publishMovieCreated(data);
        return newMovie;


    }
}
