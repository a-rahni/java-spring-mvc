
package fr.m2i.javaspringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping({"/", "/hello"})
    public String showWelcomePage(ModelMap model) {
        model.addAttribute("title", "Mon app MVC");
        model.addAttribute("message", "Hello depuis Spring MVC");
        return "hello";
    }
}
