package se360.shortlinker.restserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se360.shortlinker.restserver.model.Link;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findByUser_Id(Long id);
}
