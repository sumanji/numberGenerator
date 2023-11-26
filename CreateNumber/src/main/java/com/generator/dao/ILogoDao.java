package com.generator.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generator.entity.LogoStorage;

public interface ILogoDao extends JpaRepository<LogoStorage, Integer> {

}
