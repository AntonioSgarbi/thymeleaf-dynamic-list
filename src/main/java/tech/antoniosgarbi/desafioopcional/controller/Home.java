package tech.antoniosgarbi.desafioopcional.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Home {


    @GetMapping("/{path}")
    public ModelAndView load1(@PathVariable String path) {
        ModelAndView mv = new ModelAndView(path);
        System.out.println(path);
        return mv;
    }



}
