package com.example.ToDoList.Repository;

import com.example.ToDoList.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

    public Users findByEmail(String email);
}
