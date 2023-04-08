package com.dh.serie.service;

import com.dh.serie.event.SerieCreatedProducerEvent;
import com.dh.serie.model.Serie;
import com.dh.serie.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    private final SerieRepository repository;
    private final SerieCreatedProducerEvent serieCreated;


    public SerieService(SerieRepository repository, SerieCreatedProducerEvent serieCreated) {
        this.repository = repository;
        this.serieCreated = serieCreated;
    }

    public List<Serie> getAll() {
        return repository.findAll();
    }

    public List<Serie> getSeriesBygGenre(String genre) {
        return repository.findAllByGenre(genre);
    }

    public String create(Serie serie) {
        Serie newSerie = repository.save(serie);
        SerieCreatedProducerEvent.Data data = new SerieCreatedProducerEvent.Data();
        data.setId(newSerie.getId());
        data.setName(newSerie.getName());
        data.setGenre(newSerie.getGenre());
        serieCreated.publishNewSeriePublish(data);
        return newSerie.getId();
    }
}
