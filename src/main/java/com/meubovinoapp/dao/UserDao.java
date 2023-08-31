package com.meubovinoapp.dao;

import com.meubovinoapp.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

}
