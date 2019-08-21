package com.bolsadeideas.springboot.backend.apirest.entities;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * The <b>@NodeEntity</b> annotation is used to
 * specify that this class is an entity node in
 * the graph.
 *
 * The <b>@Id</b> and <b>@GeneratedValue</b> annotations
 * to the 'Id' field are to tell to Spring Data Neo4j that
 * this field is the internal ID and that we want the database
 * to generate the value for us.
 *
 * The others fields are mapped directly to properties of our
 * person nodes.
 *
 * The next annotation <b>@Relationship</b> deals with the relationship
 * that connects the 'Person' and the 'Movie' entities. It's specified
 * as 'ACTED_IN' because this is the relationship type that we want Neo4j
 * to define or find. The 'movies' field shows that a 'Person' can act in
 * one or more 'Movies'.
 *
 * @author Ernesto Antonio Rodriguez Acosta
 *
 */
@NodeEntity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int born;

    @Relationship(type = "ACTED_IN")
    private List<Movie> movies = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
