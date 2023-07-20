package com.example.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.entities.User;
import java.util.List;
@Repository
@Component
public interface UserRepo extends JpaRepository<User,Integer> {
    List<User> findByemail(String email);
    User findById(int id);
    
	
}
