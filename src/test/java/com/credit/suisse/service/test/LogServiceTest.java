package com.credit.suisse.service.test;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.credit.suisse.dao.LogRepository;
import com.credit.suisse.dto.LogDataResponse;
import com.credit.suisse.entity.LogEntity;
import com.credit.suisse.exception.DatabaseException;
import com.credit.suisse.exception.LogException;
import com.credit.suisse.mapper.LogMapper;
import com.credit.suisse.service.LogService;

@SpringBootTest
public class LogServiceTest {

	@InjectMocks
	private LogService logService;

	@Mock
	private LogRepository logRepository;

	@Mock
	private LogMapper logMapper;

	@Test
	public void saveLogsTest() {
		LogEntity logEntity = new LogEntity();
		Mockito.when(logMapper.convertLogDataToLogEntity(Mockito.any(), Mockito.anyDouble())).thenReturn(logEntity);
		LogDataResponse logDataResponse = new LogDataResponse(1, "scsmbstgrb", 4.0, "APPLICATION_LOG", "12345", false);
		Mockito.when(logMapper.convertLogsEntityToLogsDataResponse(Mockito.anyList()))
				.thenReturn(Arrays.asList(logDataResponse));
		logService.saveLogs("C:\\Users\\vishaarora\\Desktop\\cslogfile.txt");
	}

	@Test
	public void saveLogsTest2() {
		LogEntity logEntity = new LogEntity();
		Mockito.when(logMapper.convertLogDataToLogEntity(Mockito.any(), Mockito.anyDouble())).thenReturn(logEntity);
		LogDataResponse logDataResponse = new LogDataResponse(1, "scsmbstgrb", 4.0, "APPLICATION_LOG", "12345", false);
		Mockito.when(logMapper.convertLogsEntityToLogsDataResponse(Mockito.anyList()))
				.thenReturn(Arrays.asList(logDataResponse));
		logService.saveLogs("C:\\Users\\vishaarora\\Desktop\\cslogfile.txt");
	}

	@Test
	public void saveLogsTestException() {
		LogEntity logEntity = new LogEntity();
		Mockito.when(logMapper.convertLogDataToLogEntity(Mockito.any(), Mockito.anyDouble())).thenReturn(logEntity);
		Mockito.when(logMapper.convertLogsEntityToLogsDataResponse(Mockito.anyList()))
				.thenThrow(DatabaseException.class);
		Assertions.assertThrows(LogException.class,
				() -> logService.saveLogs("C:\\Users\\vishaarora\\Desktop\\cslogfile.txt"));
	}
}
