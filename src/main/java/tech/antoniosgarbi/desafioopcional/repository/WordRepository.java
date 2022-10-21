package tech.antoniosgarbi.desafioopcional.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.antoniosgarbi.desafioopcional.model.Word;

public interface WordRepository  extends JpaRepository<Word, Long> {
    Page<Word> findAllByValueContaining(String name, Pageable pageable);
}
