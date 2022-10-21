package tech.antoniosgarbi.desafioopcional.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.desafioopcional.model.Word;
import tech.antoniosgarbi.desafioopcional.repository.WordRepository;

@Service
public class WordService {
    private final WordRepository wordRepository;
    private final TagService tagService;

    public WordService(WordRepository wordRepository, TagService tagService) {
        this.wordRepository = wordRepository;
        this.tagService = tagService;
    }

    public Page<Word> findAll(Pageable pageable) {
        return this.wordRepository.findAll(pageable);
    }

    public Word insert(Word word) {
        word = this.wordRepository.save(word);
        return word;
    }

    public Word findById(Long id) {
        return this.findModel(id);
    }

    public Page<Word> findByName(String name, Pageable pageable) {
        return this.wordRepository.findAllByValueContaining(name, pageable);
    }

    public Word update(Word dto) {
        this.findModel(dto.getId());
        this.insert(dto);
        return dto;
    }

    private Word findModel(Long id) {
        return this.wordRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public void delete(Long id) {
        Word entity = this.findModel(id);
        this.wordRepository.delete(entity);
    }

}
