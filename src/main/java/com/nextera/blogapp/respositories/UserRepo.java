package com.nextera.blogapp.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextera.blogapp.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
