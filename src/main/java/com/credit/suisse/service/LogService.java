package com.credit.suisse.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credit.suisse.dao.LogRepository;
import com.credit.suisse.dto.LogData;
import com.credit.suisse.dto.LogDataResponse;
import com.credit.suisse.entity.LogEntity;
import com.credit.suisse.exception.DatabaseException;
import com.credit.suisse.exception.LogException;
import com.credit.suisse.mapper.LogMapper;
import com.credit.suisse.utils.LogUtils;
import com.google.gson.Gson;

@Service
public class LogService {

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private LogMapper logMapper;

	public List<LogDataResponse> saveLogs(String logFilePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
			List<LogData> logsData = reader.lines().map(data -> new Gson().fromJson((String) data, LogData.class))
					.collect(Collectors.toList());
			return iterateLogs(logsData);
		} catch (IOException ioException) {
			throw new LogException(logFilePath, logFilePath);
		}

	}

	private List<LogDataResponse> iterateLogs(List<LogData> logsData) {
		try {
			List<LogEntity> logsEntity = new ArrayList<>();
			Set<String> ids = new HashSet<>();

			CollectionUtils.emptyIfNull(logsData).stream().forEach(logData -> {
				if (!ids.contains(logData.getId())) {
					ids.add(logData.getId());
					Double eventDuration = LogUtils.calculateEventDuration(logData, logsData);
					LogEntity logEntity = logMapper.convertLogDataToLogEntity(logData, eventDuration);
					logsEntity.add(logEntity);
				}
			});

			logRepository.saveAll(logsEntity);
			return logMapper.convertLogsEntityToLogsDataResponse(logsEntity);
		} catch (DatabaseException exception) {
			throw new LogException();
		}
	}

	public List<LogEntity> getAllLogs() {
		return logRepository.findAll();
	}

}
