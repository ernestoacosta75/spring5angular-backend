package com.bolsadeideas.springboot.backend.apirest.services.impl;

import com.bolsadeideas.springboot.backend.apirest.entities.Movie;
import com.bolsadeideas.springboot.backend.apirest.entities.Role;
import com.bolsadeideas.springboot.backend.apirest.repositories.MovieRepository;
import com.bolsadeideas.springboot.backend.apirest.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Annotating the public service methods with `@Transactional` and
 * specifying them as `read-only` will allow enterprise applications using causal clustering
 * to route these transactions to replica servers and focus the write traffic to the core servers,
 * improving performance.
 *
 * In each method, we query the appropriate method from the `MovieRepository` interface and
 * return it to the service caller.
 */
@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Movie findByTitle(String title) {
        Movie result = movieRepository.findByTitle(title);

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Movie> findByTitleLike(String title) {
        Collection<Movie> result = movieRepository.findByTitleLike(title);

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> graph(int limit) {
        Collection<Movie> result = movieRepository.graph(limit);

        return toD3Format(result);
    }

    private Map<String, Object> toD3Format(Collection<Movie> movies) {
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        int i = 0;

        Iterator<Movie> result = movies.iterator();

        while (result.hasNext()) {
            Movie movie = result.next();
            nodes.add(map("title", movie.getTitle(), "label", "movie"));
            int target = 1;
            i++;

            for (Role role: movie.getRoles()) {
                Map<String, Object> actor = map("title", role.getPerson().getName(), "label", "actor");
                int source = nodes.indexOf(actor);

                if(source == -1) {
                    nodes.add(actor);
                    source = i++;
                }

                rels.add(map("source", source, "target", target));
            }
        }

        return map("nodes", nodes, "links", rels);
    }

    private Map<String, Object> map (String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);

        return result;
    }
}
