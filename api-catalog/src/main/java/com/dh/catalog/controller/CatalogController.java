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

	@GetMapping("/movie/online/{genre}")
	public ResponseEntity<List<MovieServiceClient.MovieDto>> getMoviesGenre(@PathVariable String genre) {
		return ResponseEntity.ok(service.getMovieByGenre(genre));
	}

	@GetMapping("/series/online/{genre}")
	public ResponseEntity<List<SerieServiceClient.SerieDto>> getSeriesGenre(@PathVariable String genre) {
		return ResponseEntity.ok(service.getSerieByGenre(genre));
	}

	@GetMapping("/movieOffline/{genre}")
	@ResponseStatus(HttpStatus.OK)
	public List<MovieServiceClient.MovieDto> findAllMoviesByGenreOffline(@PathVariable String genre)  {
		return service.getMovieByGenreOffline(genre);
	}


	@GetMapping("/serieOffline/{genre}")
	@ResponseStatus(HttpStatus.OK)
	List<SerieServiceClient.SerieDto> findAllSeriesByGenreOffline(@PathVariable String genre){
		return service.findAllSeriesOffline(genre);
	}


}
