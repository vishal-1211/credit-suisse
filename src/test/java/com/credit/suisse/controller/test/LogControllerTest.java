package com.credit.suisse.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.credit.suisse.controller.LogController;
import com.credit.suisse.dto.LogDataResponse;
import com.credit.suisse.exception.LogException;
import com.credit.suisse.service.LogService;

@SpringBootTest
public class LogControllerTest {

	@InjectMocks
	private LogController logController;

	@Mock
	private LogService logService;

	@Mock
	private BufferedReader reader;

	@Test
	public void saveLogsTest() {
		LogDataResponse logDataResponse = new LogDataResponse(1, "scsmbstgrb", 4.0, "APPLICATION_LOG", "12345", false);
		Mockito.when(logService.saveLogs(Mockito.any(String.class))).thenReturn(Arrays.asList(logDataResponse));
		List<LogDataResponse> logList = (List<LogDataResponse>) logController
				.saveLogs("C:\\Users\\vishaarora\\Desktop\\cslogfile.txt");
		LogDataResponse actualResponse = logList.get(0);
		assertEquals(false, actualResponse.getAlert());
	}

	@Test
	public void saveLogsTestException() {
		Mockito.when(logService.saveLogs(Mockito.any(String.class))).thenThrow(LogException.class);
		Assertions.assertThrows(LogException.class, () -> logController.saveLogs("xyz"));
	}
}
