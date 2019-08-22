package com.bolsadeideas.springboot.backend.apirest.services;

import com.bolsadeideas.springboot.backend.apirest.entities.Movie;

import java.util.Collection;
import java.util.Map;

public interface MovieService {

    public Movie findByTitle(String title);
    public Collection<Movie> findByTitleLike(String title);
    public Map<String, Object> graph(int limit);
}
