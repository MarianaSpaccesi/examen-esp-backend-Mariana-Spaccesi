package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;

import com.dh.catalog.client.SerieServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final MovieServiceClient movieServiceClient;
	private final SerieServiceClient serieFeign;

	public CatalogController(MovieServiceClient movieServiceClient, SerieServiceClient serieFeign) {
		this.movieServiceClient = movieServiceClient;
		this.serieFeign = serieFeign;
	}

	@GetMapping("/movie/{genre}")
	ResponseEntity<List<MovieServiceClient.MovieDto>> getMoviesGenre(@PathVariable String genre) {
		return ResponseEntity.ok(movieServiceClient.getMovieByGenre(genre));
	}

	@GetMapping("/series/{genre}")
	ResponseEntity<List<SerieServiceClient.SerieDto>> getSeriesGenre(@PathVariable String genre) {
		return ResponseEntity.ok(serieFeign.getByGenre(genre));
	}

}
