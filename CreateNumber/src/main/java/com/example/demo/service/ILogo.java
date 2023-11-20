package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.LogoStorage;

public interface ILogo {
	
	Optional<LogoStorage> getLogoInfo(Integer logoId);
	void saveLogoInfo(LogoStorage logo);

}
