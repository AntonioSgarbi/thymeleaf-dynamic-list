package tech.antoniosgarbi.desafioopcional.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.desafioopcional.model.Tag;
import tech.antoniosgarbi.desafioopcional.repository.TagRepository;

import java.util.List;


@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Page<Tag> findAll(Pageable pageable) {
        return this.tagRepository.findAll(pageable);
    }

    public List<Tag> findAllList() {
        return this.tagRepository.findAll();
    }

    public Page<Tag> findByName(String name, Pageable pageable) {
        return this.tagRepository.findAllByValueContaining(name, pageable);
    }

    public Tag insert(Tag model) {
        model = this.tagRepository.save(model);
        return model;
    }

    public Tag update(Tag tag) {
        this.findModel(tag.getId());
        this.insert(tag);
        return tag;
    }

    public Tag findById(Long id) {
        return this.findModel(id);
    }

    public void delete(Long id) {
        this.tagRepository.deleteById(id);
    }

    private Tag findModel(Long id) {
        return this.tagRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
