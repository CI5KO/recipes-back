package com.recipesback.service;

import com.recipesback.model.Tag;
import com.recipesback.repository.TagRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository repository;

    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public List<Tag> getAllTags() {
        return repository.findAll();
    }

    public Optional<Tag> getTagById(Long id) {
        return repository.findById(id);
    }

    public Tag createTag(Tag tag) {
        return repository.save(tag);
    }

    public Optional<Tag> updateTag(Long id, Tag tag) {
        return repository.findById(id)
                .map(existing -> {
                    tag.setId(id);
                    return repository.save(tag);
                });
    }

    public boolean deleteTag(Long id) {
        return repository.findById(id)
                .map(tag -> {
                    repository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
