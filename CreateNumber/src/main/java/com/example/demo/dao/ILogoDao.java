package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LogoStorage;

public interface ILogoDao extends JpaRepository<LogoStorage, Integer> {

}
