
package com.dmm.task.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmm.task.data.entity.User;

public interface UsersRepository extends JpaRepository<User, String> {

}