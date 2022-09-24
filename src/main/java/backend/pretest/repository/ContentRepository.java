package backend.pretest.repository;

import backend.pretest.entity.Content;
import backend.pretest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Integer> {
}
