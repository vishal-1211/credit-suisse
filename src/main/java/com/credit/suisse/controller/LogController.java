package com.credit.suisse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.credit.suisse.dto.LogDataResponse;
import com.credit.suisse.entity.LogEntity;
import com.credit.suisse.service.LogService;

@RestController
public class LogController {

	@Autowired
	private LogService logService;

	@PostMapping(value = "/save")
	public List<LogDataResponse> saveLogs(@RequestBody String logFilePath) {
		return logService.saveLogs(logFilePath);
	}

	@GetMapping(value = "/all")
	public List<LogEntity> getAllLogs() {
		return logService.getAllLogs();
	}
}
