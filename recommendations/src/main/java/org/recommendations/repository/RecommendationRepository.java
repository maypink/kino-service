package org.recommendations.repository;

import jakarta.transaction.Transactional;
import org.recommendations.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, UUID> {

//    @Query(value = "SELECT * from recommendations_table where user_id:=userId", nativeQuery = true)
    List<Recommendation> findByUserId(UUID userId);

//    @Query(value = "SELECT * from recommendations_table where user_id = ?1 and film_id = ?2", nativeQuery = true)
    List<Recommendation> findByUserIdAndFilmId(UUID userId, UUID filmId);

    @Modifying
    @Transactional
    @Query("update Recommendation r set r.score = ?3, r.dateTime = ?2 where r.id = ?1")
    void updateById(UUID id, LocalDateTime dateTime, Double score);
}
