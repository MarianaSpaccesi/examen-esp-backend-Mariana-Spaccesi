package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;

import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private CatalogService service;

	public CatalogController(CatalogService service) {
		this.service = service;
	}

	@GetMapping("/movie/{genre}")
	ResponseEntity<List<MovieServiceClient.MovieDto>> getMoviesGenre(@PathVariable String genre) {
		return ResponseEntity.ok(service.getMovieByGenre(genre));
	}

	@GetMapping("/series/{genre}")
	ResponseEntity<List<SerieServiceClient.SerieDto>> getSeriesGenre(@PathVariable String genre) {
		return ResponseEntity.ok(service.getSerieByGenre(genre));
	}

	@GetMapping("/movieOffline/{genre}")
	@ResponseStatus(HttpStatus.OK)
	List<MovieServiceClient.MovieDto> findAllMoviesByGenreOffline(@PathVariable String genre){
		return service.findAllMoviesOffline(genre);
	}


	@GetMapping("/serieOffline/{genre}")
	@ResponseStatus(HttpStatus.OK)
	List<SerieServiceClient.SerieDto> findAllSeriesByGenreOffline(@PathVariable String genre){
		return service.findAllSeriesOffline(genre);
	}


}
