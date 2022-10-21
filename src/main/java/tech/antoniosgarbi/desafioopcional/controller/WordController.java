package tech.antoniosgarbi.desafioopcional.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.antoniosgarbi.desafioopcional.model.Word;
import tech.antoniosgarbi.desafioopcional.service.TagService;
import tech.antoniosgarbi.desafioopcional.service.WordService;

@Controller
@RequestMapping("/word")
public class WordController {
    private final WordService wordService;
    private final TagService tagService;

    public WordController(WordService wordService, TagService tagService) {
        this.wordService = wordService;
        this.tagService = tagService;
    }

    @GetMapping
    public ModelAndView findAll(Pageable pageable) {
        ModelAndView mv = new ModelAndView("word/list.html");
        mv.addObject("page", wordService.findAll(pageable));

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView createForm(@RequestParam(required = false) Long id) {
        ModelAndView mv = new ModelAndView("word/form.html");
        if(id != null)
            mv.addObject("model", this.wordService.findModel(id));
        else
            mv.addObject("model", new Word());

        mv.addObject("tagList", this.tagService.findAllList());

        return mv;
    }

    @PostMapping("/new")
    public ModelAndView insert(Word word, BindingResult bindingResult) {
        System.out.println(word);

        ModelAndView mv = new ModelAndView("word/form.html");

        if(bindingResult.hasErrors()) {
            mv.addObject("model", word);
            return mv;
        }

        if(word.getId() != null) {
            word = this.wordService.update(word);
            mv.addObject("mensagem", "Palavra atualizada com sucesso");
        } else {
            word = this.wordService.insert(word);
            mv.addObject("mensagem", "Palavra cadastrada com sucesso");
        }
        mv.addObject("model", new Word());
        mv.addObject("tagList", this.tagService.findAllList());

        return mv;
    }


    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        this.wordService.delete(id);
        ModelAndView mv = new ModelAndView("word/list.html");
        mv.addObject("page", wordService.findAll(Pageable.unpaged()));

        return mv;
    }

    @PostMapping("/news")
    public ModelAndView insert1(@RequestBody Word dto) {
        ModelAndView mv = new ModelAndView("word/form.html");

        if(dto.getId() != null) {
            dto = this.wordService.update(dto);
        } else {
            dto = this.wordService.insert(dto);
        }
        mv.addObject("model", dto);

        return mv;
    }

}
