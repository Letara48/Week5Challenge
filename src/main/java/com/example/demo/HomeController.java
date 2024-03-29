package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/")
    public String listbooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String bookForm(Model model) {
        model.addAttribute("book", new Book());
        return "bookform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "bookform";
        }
        bookRepository.save(book);
        return "redirect:/";
    }
    @RequestMapping("/about")
    public String dessert(){
        return "about";
    }

    @RequestMapping("/details/{id}")
    public String showBook(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("book", bookRepository.findById(id).get());
        return "show";

    }
    @RequestMapping("/update/{id}")
    public String updateBook(@PathVariable("id") long id,
                             Model model){
        model.addAttribute("book", bookRepository.findById(id).get());
        return "bookform";
    }
    @RequestMapping("/delete/{id}")
    public String delBook(@PathVariable("id") long id){
        bookRepository.deleteById(id);
        return "redirect:/";
    }
}
