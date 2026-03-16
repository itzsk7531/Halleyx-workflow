package com.example.Halleyx.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Halleyx.model.User;

public interface UserRepository extends JpaRepository<User,String> {

    long countByRole(String role);

    List<User> findByRole(String role);

}