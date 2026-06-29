package com.example.ToDoList.Repository;

import com.example.ToDoList.Model.DoneTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoneTaskRepo extends JpaRepository<DoneTask,Integer> {
    DoneTask findByIdAndEmail(int id, String email);
}
