package com.example.ToDoList.Controller;

import com.example.ToDoList.Service.ToDoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class ToDoController {

    @Autowired
    ToDoService service;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/main")
    public String mains(Model model, HttpSession session){

        String email = session.getAttribute("email").toString();
        String name = session.getAttribute("name").toString();

        model.addAttribute("email",email);
        model.addAttribute("name",name);

        model.addAttribute("tasks",service.tasks(email));
        return "main";
    }

    @GetMapping("/deletedPost")
    public String deletedPost(Model model,HttpSession session){

        String email = session.getAttribute("email").toString();
        String name = session.getAttribute("name").toString();

        model.addAttribute("email",email);
        model.addAttribute("name",name);

        model.addAttribute("tasks",service.deletedTasks(email));

        return "deletedPost";
    }

    @GetMapping("/createPost")
    public String create(){
        return "createPost";
    }

    @GetMapping("/retrieve")
    public String retrieve(@RequestParam("id") int id, Model model,HttpSession session){

        String email = session.getAttribute("email").toString();
        String name = session.getAttribute("name").toString();

        model.addAttribute("email",email);
        model.addAttribute("name",name);

        service.retrievePost(id,email);

        return "redirect:/deletedPost";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id,Model model,HttpSession session){

        String email = session.getAttribute("email").toString();
        String name = session.getAttribute("name").toString();

        model.addAttribute("email",email);
        model.addAttribute("name",name);
        model.addAttribute("Edit",service.getEdit(id,email));

        return "edit";
    }

    @PostMapping("/register")
    public String register(@RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password){
        service.register(name,email,password);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session){
        if(service.login(email,password)){
            String sessionId = session.getId();
            session.setAttribute("sid",sessionId);
            session.setAttribute("email", email);
            String name = service.getName(email);
            session.setAttribute("name", name);
            return "redirect:/main";
        }
        else {
            return "redirect:/login";
        }
    }

    @PostMapping("/createPost")
    public String create(@RequestParam("title") String title,
                        @RequestParam("taskTime") @DateTimeFormat(pattern="HH:mm") LocalTime lt,
                        @RequestParam("lastDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate ld,
                        @RequestParam("content") String content,
                        HttpSession session,
                        Model model){

        String email = session.getAttribute("email").toString();
        String name = session.getAttribute("name").toString();

        model.addAttribute("email",email);
        model.addAttribute("name",name);

        service.createPost(email,title,lt,ld,content);
        return "redirect:/main";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id,HttpSession session){
        String email = session.getAttribute("email").toString();
        service.delPost(id,email);
        return "redirect:/main";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") int id,
                         @RequestParam("title") String title,
                         @RequestParam("taskTime") @DateTimeFormat(pattern="HH:mm") LocalTime lt,
                         @RequestParam("lastDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate ld,
                         @RequestParam("content") String content,
                         HttpSession session,
                         Model model){

        String email = session.getAttribute("email").toString();
        String name = session.getAttribute("name").toString();

        model.addAttribute("email",email);
        model.addAttribute("name",name);

        service.update(id,email,title,lt,ld,content);
        return "redirect:/main";
    }
}
