package com.bolsadeideas.springboot.backend.apirest.repositories;


import com.bolsadeideas.springboot.backend.apirest.entities.Movie;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * This interface extends the <b>'Neo4jRepository'</b> (which itself is
 * an extension of the 'CRUDRepository' from Spring Data) and set up the
 * queries to retrieve the data.
 *
 * One method is annotated with <b>@Query</b>, because the syntax of the first
 * two methods are derived for us, so we do not have to write the queries manually.
 * The annotated method defines a custom query.
 *
 * In the first and second queries ('findByTitle' and 'findByTitleLike') are passing in
 * a particular movie title for the search. The last query will return graph results an
 * can optionally pass in limit for how many results should be returned from the graph.
 *
 * @author Ernesto Antonio Rodriguez Acosta
 */
public interface MovieRepository extends Neo4jRepository<Movie, Long> {

    Movie findByTitle(@Param("title") String title);

    Collection<Movie> findByTitleLike(@Param("title") String title);

    @Query("MATCH (m:Movie)<- [r:ACTED_IN]-(a:Person) RETURN m, r, a LIMIT {limit}")
    Collection<Movie> graph(@Param("limit") int limit);
}
