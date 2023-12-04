package kino.repository;

import kino.model.FilmRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

@Repository
public interface FilmRatingRepository extends JpaRepository<FilmRating, UUID> {

    @Query(value = "SELECT * from films_ratings where user_id = ?1", nativeQuery = true)
    List<FilmRating> findByUserId(UUID userId);

    @Query(value = "SELECT * from films_ratings where user_id = ?1 and film_id = ?2", nativeQuery = true)
    List<FilmRating> findByUserIdAndFilmId(UUID userId, UUID filmId);

    @Query(value = "SELECT * from films_ratings", nativeQuery = true)
    List<FilmRating> findAllFilms();
}
