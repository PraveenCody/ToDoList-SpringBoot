package com.example.ToDoList.Model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Getter
@Setter
public class Tasks {

    public Tasks(String email,String title,LocalTime tt,LocalDate ld,String content){
        this.email = email;
        this.title = title;
        this.taskTime = tt;
        this.lastDate = ld;
        this.content = content;
    }

    public Tasks(int id,String email,String title,LocalTime tt,LocalDate ld,String content){
        this.id = id;
        this.email = email;
        this.title = title;
        this.taskTime = tt;
        this.lastDate = ld;
        this.content = content;
    }

    public Tasks(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true)
    private String email;

    private String title;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime taskTime;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastDate;

    private String content;

}
