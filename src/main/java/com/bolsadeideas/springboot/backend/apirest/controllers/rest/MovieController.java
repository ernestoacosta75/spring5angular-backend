package com.bolsadeideas.springboot.backend.apirest.controllers.rest;

import com.bolsadeideas.springboot.backend.apirest.entities.Movie;
import com.bolsadeideas.springboot.backend.apirest.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

/**
 * This REST Controller exposes the endpoints we ant to use for
 * retrieving Movie data over HTTP.
 * The <b>@ResponseBody</> annotation will render the results as
 * JSON (Default) in the response.
 */
@RestController
@RequestMapping("/")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     *
     * @param title
     * @return
     */
    @GetMapping("/movie")
    public Movie findByTitle(@RequestParam String title) {
        return movieService.findByTitle(title);
    }

    @GetMapping("/movies")
    public Collection<Movie> findByTitleLike(@RequestParam String title) {
        return movieService.findByTitleLike(title);
    }

    @GetMapping("/graph")
    public Map<String, Object> graph (@RequestParam(value = "limit", required = false) Integer limit) {
        return movieService.graph(limit == null ? 100 : limit);
    }
}
