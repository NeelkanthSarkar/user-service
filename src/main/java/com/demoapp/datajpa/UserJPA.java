package com.demoapp.datajpa;



import org.springframework.data.jpa.repository.JpaRepository;

import com.demoapp.entities.User;

public interface UserJPA extends JpaRepository<User, Integer>{

}
