package tech.antoniosgarbi.desafioopcional.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.antoniosgarbi.desafioopcional.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Page<Tag> findAllByValueContaining(String value, Pageable pageable);

}
