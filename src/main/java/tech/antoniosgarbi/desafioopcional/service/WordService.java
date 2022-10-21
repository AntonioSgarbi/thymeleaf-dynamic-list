package tech.antoniosgarbi.desafioopcional.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.desafioopcional.model.Word;
import tech.antoniosgarbi.desafioopcional.repository.WordRepository;

@Service
public class WordService {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Page<Word> findAll(Pageable pageable) {
        return this.wordRepository.findAll(pageable);
    }

    public Word insert(Word word) {
        word = this.wordRepository.save(word);
        return word;
    }

    public Word update(Word word) {
        this.findModel(word.getId());
        this.insert(word);
        return word;
    }

    public Word findModel(Long id) {
        return this.wordRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    public void delete(Long id) {
        Word entity = this.findModel(id);
        this.wordRepository.delete(entity);
    }

}
