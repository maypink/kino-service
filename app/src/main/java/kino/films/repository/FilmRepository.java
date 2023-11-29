package kino.films.repository;

import kino.films.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FilmRepository extends JpaRepository<Film, UUID> {

    List<Film> findByFilmTitle(String title);

    List<Film> findByGenre(String genre);

    List<Film> findByYear(Integer year);

    List<Film> getAllFilms();
}
