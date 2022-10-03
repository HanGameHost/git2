package com.spring.test.controllers;

import com.spring.test.models.Computer;
import com.spring.test.models.Keyboard;
import com.spring.test.models.Post;
import com.spring.test.repository.CompRepository;
import com.spring.test.repository.KeyBoardRepository;
import com.spring.test.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController  {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CompRepository compRepository;
    @Autowired
    private KeyBoardRepository keyBoardRepository;
    @GetMapping("/")
    public String blogMain(Model model)
    {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

   @GetMapping("/blog/add")
    public String blogAdd(Model model)
    {
        return "blog-add";
    }

    @GetMapping("/blog/addKeyboard")
    public String blogAddComp(Model model){
        return "blog-addComp";
    }

    @PostMapping("/blog/addKeyboard")
    public String blogPostAddComp(@RequestParam String name,
                                  @RequestParam String type,
                                  @RequestParam String switches,
                                  @RequestParam Integer formPer,
                                  @RequestParam Integer keyNums,
                                  Model model)
    {
        Keyboard keyboard = new Keyboard(name, type, switches, formPer, keyNums);
        keyBoardRepository.save(keyboard);
        return "redirect:/";
    }

    @GetMapping("/blog/addComp")
    public String blogAddKeyboard(Model model){
        return "blog-addKeyboard";
    }

    @PostMapping("/blog/addComp")
    public String blogPostAddKeyboard(@RequestParam String chip,
                                  @RequestParam String drip,
                                  @RequestParam String chipset,
                                  @RequestParam Integer ramMb,
                                  @RequestParam Integer weed,
                                  Model model)
    {
        Computer comp = new Computer(chip, drip, chipset, ramMb, weed);
        compRepository.save(comp);
        return "redirect:/";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text, Model model)
    {
        Post post = new Post(title, anons, full_text);
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("/blog/filter")
    public String blogFilter(Model model)
    {
        return "blog-filter";
    }

    @PostMapping("/blog/filter/result")
    public String blogResult(@RequestParam(required = false) String chip ,
                             @RequestParam(required = false) String drip,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) Integer formPer, Model model)
    {
        //List<Post> result = postRepository.findByTitleContains(title);
//        List<Post> result = postRepository.findLikeTitle(title);
        if(chip != null){
            List<Computer> compResContains = compRepository.findByChipContains(chip);
            model.addAttribute("compResContains", compResContains);
        }
        if (drip != null){
            List<Computer> compRes = compRepository.findByDrip(drip);
            model.addAttribute("compRes", compRes);
        }
        if (name != null){
            List<Keyboard> keyName = keyBoardRepository.findByNameContaining(name);
            model.addAttribute("keyName", keyName);
        }
        if (formPer != null){

            List<Keyboard> keyForm = keyBoardRepository.findByFormPer(formPer);
            model.addAttribute("keyForm", keyForm);
        }
        return "blog-filter";
    }



    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        if(!postRepository.existsById(id))
        {
            return "redirect:/blog";
        }
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model)
    {
        if(!postRepository.existsById(id))
        {
            return "redirect:/";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate (@PathVariable("id")long id,
                                  @RequestParam String title,
                                  @RequestParam String anons,
                                  @RequestParam String full_text,
                                  Model model)
    {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogBlogDelete(@PathVariable("id")long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/";
    }
}
