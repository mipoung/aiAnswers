package com.aiAnswers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiAnswers.dto.DevHistDTO;
import com.aiAnswers.mapper.DevHistMapper;

@Service
public class DevHistServiceImpl implements DevHistService {

	@Autowired
	DevHistMapper devHistMapper;
	
	@Override
	public List<DevHistDTO> findAll() {
		return devHistMapper.findAll();
	}

}
