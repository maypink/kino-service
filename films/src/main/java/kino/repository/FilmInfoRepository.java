package kino.repository;

import kino.model.FilmInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilmInfoRepository extends JpaRepository<FilmInfo, UUID> {

}
