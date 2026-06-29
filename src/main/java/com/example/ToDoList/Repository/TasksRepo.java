package com.example.ToDoList.Repository;

import com.example.ToDoList.Model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepo extends JpaRepository<Tasks,Integer> {

    Tasks findByIdAndEmail(int id, String email);

}
