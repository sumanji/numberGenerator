package com.generator.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generator.entity.UserInfo;

public interface IUserDao extends JpaRepository<UserInfo, Integer> {

	@Query("SELECT u FROM UserInfo u WHERE u.userName = :userName and u.password = :password")
	UserInfo findUserByuserNameandPassword(@Param("userName") String userName, @Param("password") String password);

}
