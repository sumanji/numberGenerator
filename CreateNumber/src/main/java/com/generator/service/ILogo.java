package com.generator.service;

import java.util.Optional;

import com.generator.entity.LogoStorage;

public interface ILogo {
	
	Optional<LogoStorage> getLogoInfo(Integer logoId);
	void saveLogoInfo(LogoStorage logo);

}
