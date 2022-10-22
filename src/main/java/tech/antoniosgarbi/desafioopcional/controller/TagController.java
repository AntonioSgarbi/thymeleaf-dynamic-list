package tech.antoniosgarbi.desafioopcional.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.antoniosgarbi.desafioopcional.model.Tag;
import tech.antoniosgarbi.desafioopcional.service.TagService;

@Controller
@RequestMapping("/tag")
@CrossOrigin(origins = "*")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ModelAndView findAll(Pageable pageable) {
        ModelAndView mv = new ModelAndView("tag/list.html");
        mv.addObject("page", tagService.findAll(pageable));

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView createForm() {
        ModelAndView mv = new ModelAndView("tag/form.html");
        mv.addObject("model", new Tag());

        return mv;
    }

    @PostMapping("/new")
    public ModelAndView insert(Tag tag) {
        ModelAndView mv = new ModelAndView("tag/form.html");

        if(tag.getId() != null) {
            tag = this.tagService.update(tag);
        } else {
            tag = this.tagService.insert(tag);
        }
        mv.addObject("model", tag);

        return mv;
    }

    @GetMapping("/new/{id}")
    public ModelAndView loadToUpdate(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("tag/form.html");
        mv.addObject("model", this.tagService.findById(id));

        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("tag/list.html");

        this.tagService.delete(id);

        mv.addObject("page", this.tagService.findAll(Pageable.unpaged()));
        mv.addObject("message", "record deleted");

        return mv;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<Tag>> findByName(@PathVariable String name, Pageable pageable) {
        return ResponseEntity.ok(tagService.findByName(name, pageable));
    }
}
