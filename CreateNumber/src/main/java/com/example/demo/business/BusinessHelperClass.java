package com.example.demo.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RandomNumberDetail;
import com.example.demo.entity.SessionManagement;
import com.example.demo.service.IRandomNumber;
import com.example.demo.service.ISessionManager;

@Service
public class BusinessHelperClass implements IBusinessHelper {

	@Autowired
	IRandomNumber numberService;
	
	@Autowired
	ISessionManager sessionService;

	@Override
	public boolean createNumber(Integer number) {
		// TODO Auto-generated method stub

		RandomNumberDetail response = null;
		if (number != null) {
			try {
				Date currentDate = new Date(System.currentTimeMillis());
				RandomNumberDetail numberEntity = new RandomNumberDetail();
				numberEntity.setNumberInsertionDate(currentDate);
				numberEntity.setRandomNumber(number);
				response = numberService.createNumber(numberEntity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return response != null;
	}

	@Override
	public boolean isSessionActive(String uuid) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = sessionService.isSessionActive(uuid);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;	
	}

	@Override
	public void createSession(String userName,String cookie,String uuid) {
		// TODO Auto-generated method stub	
		SessionManagement session = new SessionManagement();
		session.setUserName(userName);
		session.setCookie(cookie);
		session.setUserUUID(uuid);
		try {
			sessionService.createSession(session);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
