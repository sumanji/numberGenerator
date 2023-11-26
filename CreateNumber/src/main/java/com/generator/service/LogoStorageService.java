package com.generator.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.dao.ILogoDao;
import com.generator.entity.LogoStorage;

@Service
public class LogoStorageService implements ILogo {

	@Autowired
    private ILogoDao logodao;
   
	@Override
	public Optional<LogoStorage> getLogoInfo(Integer logoId) {
		return logodao.findById(logoId);
	}
	
	@Override
	public void saveLogoInfo(LogoStorage logo) {
        logodao.save(logo);		
	}
    

}
