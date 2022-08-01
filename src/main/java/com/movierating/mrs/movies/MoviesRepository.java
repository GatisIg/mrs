package com.movierating.mrs.movies;

import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long> {

    @Query("" + "SELECT CASE WHEN COUNT(m) > 0 THEN " +
                "TRUE ELSE FALSE END " +
                "FROM Movies m " +
                "WHERE m.title = ?1"
    )
    Boolean selectTitleIfExists(String title);

    Optional<Movies> getMoviesByTitle(String title);

}
