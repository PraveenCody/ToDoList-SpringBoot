package com.example.ToDoList.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class DoneTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;

    private String title;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime taskTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastDate;

    private String content;
}