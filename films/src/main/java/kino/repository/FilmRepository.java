package kino.repository;

import kino.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FilmRepository extends JpaRepository<Film, UUID> {

    List<Film> findByTitle(String title);

    @Query(value = "SELECT * FROM films WHERE id IN\n" +
            "(SELECT film_id FROM films_genres WHERE genre_id IN (\n" +
            "SELECT id from genres where genre=:genre))", nativeQuery = true)
    List<Film> findByGenre(String genre);

    List<Film> findByYear(Integer year);

    List<Film> findAll();

    List<Film> findByTitleAndYear(String title, Integer year);

    @Query(value = "SELECT * FROM films WHERE film_info_id IN\n" +
            "(SELECT id FROM films_info where tmdb_id=:tmdbId)", nativeQuery = true)
    List<Film> findByTmdbId(String tmdbId);
}
