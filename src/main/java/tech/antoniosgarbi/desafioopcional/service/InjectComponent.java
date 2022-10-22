package tech.antoniosgarbi.desafioopcional.service;

import org.springframework.stereotype.Component;
import tech.antoniosgarbi.desafioopcional.model.Tag;
import tech.antoniosgarbi.desafioopcional.model.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InjectComponent {
    private final TagService tagService;
    private final WordService wordService;

    List<Tag> tags;

    public InjectComponent(TagService tagService, WordService wordService) {
        this.tagService = tagService;
        this.wordService = wordService;
        List<String> tagValues = Arrays.asList("Noun", "Proper Noun", "Commom Noun", "Verb", "Adverb");
        tags = new ArrayList<>(5);

        for (String value : tagValues) {
            Tag t = new Tag(null, value);
            t = this.tagService.insert(t);
            tags.add(t);
        }

        List<String> wordValues = Arrays.asList("Antônio", "Amanda", "Anna", "Bárbara", "Bianca");

        for (String value : wordValues) {
            Word w = new Word(null, value, List.of(tags.get(0), tags.get(1)));
            w = this.wordService.insert(w);
        }
    }

}
