package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ILogoDao;
import com.example.demo.entity.LogoStorage;

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
