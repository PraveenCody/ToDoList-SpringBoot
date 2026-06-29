package com.example.ToDoList.Service;

import com.example.ToDoList.Model.DoneTask;
import com.example.ToDoList.Model.Tasks;
import com.example.ToDoList.Model.Users;
import com.example.ToDoList.Repository.DoneTaskRepo;
import com.example.ToDoList.Repository.TasksRepo;
import com.example.ToDoList.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ToDoService {

    @Autowired
    UsersRepo repo1;

    @Autowired
    TasksRepo repo2;

    @Autowired
    DoneTaskRepo repo3;


    public void register(String name, String email, String password) {
        Users user = new Users(name,email,password);
        repo1.save(user);
    }

    public boolean login(String email, String password) {

        Users user = repo1.findByEmail(email);

        if(user.getPassword().equals(password)){
           return true;
        }
        else{
            return false;
        }
    }

    public String getName(String email) {
        Users user = repo1.findByEmail(email);
        return user.getName();
    }

    public void createPost(String email,String title, LocalTime lt, LocalDate ld, String content) {
        Tasks task = new Tasks(email,title,lt,ld,content);
        repo2.save(task);
    }

    public List<Tasks> tasks(String email) {
        List<Tasks> userpost = new ArrayList<Tasks>();

        List<Tasks> all = repo2.findAll();

        for(Tasks task:all){
            if(Objects.equals(task.getEmail(), email)){
                userpost.add(task);
            }
        }

        return userpost;
    }

    public List<DoneTask> deletedTasks(String email) {
        List<DoneTask> userpost = new ArrayList<DoneTask>();

        List<DoneTask> all = repo3.findAll();

        for(DoneTask task:all){
            if(task.getEmail().equals(email)){
                userpost.add(task);
            }
        }
        return userpost;
    }

    public void delPost(int id, String email) {

        Tasks t = repo2.findByIdAndEmail(id,email);
        DoneTask d = new DoneTask();
        d.setTitle(t.getTitle());
        d.setContent(t.getContent());
        d.setTaskTime(t.getTaskTime());
        d.setLastDate(t.getLastDate());
        d.setEmail(t.getEmail());
        repo3.save(d);
        repo2.delete(t);

    }

    public void retrievePost(int id, String email) {

        DoneTask d = repo3.findByIdAndEmail(id,email);
        Tasks t = new Tasks();
        t.setTitle(d.getTitle());
        t.setContent(d.getContent());
        t.setTaskTime(d.getTaskTime());
        t.setLastDate(d.getLastDate());
        t.setEmail(d.getEmail());
        repo2.save(t);
        repo3.delete(d);

    }

    public Tasks getEdit(int id, String email) {
        System.err.println(id);
        return repo2.findByIdAndEmail(id,email);
    }

    public void update(int id, String email, String title, LocalTime lt, LocalDate ld, String content) {
        Tasks t = repo2.findByIdAndEmail(id,email);
        createPost(email,title,lt,ld,content);
        repo2.delete(t);
    }
}
