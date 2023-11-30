package kino.repository;

import kino.model.Genre;
import kino.utils.GenreResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {

    List<Genre> findByGenre(String genre);
}
