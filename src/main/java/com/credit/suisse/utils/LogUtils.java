package com.credit.suisse.utils;

import java.util.List;
import java.util.Optional;

import com.credit.suisse.dto.LogData;
import com.credit.suisse.enums.State;

public class LogUtils {

	private LogUtils() {
		// this is a private constructor for the util class
	}

	public static Double calculateEventDuration(LogData logData, List<LogData> logsData) {
		return calculateDurationByState(logData, logsData);
	}

	public static Double calculateDurationByState(LogData logData, List<LogData> logsData) {
		Double eventDuration = null;
		if (logData.getState().equals(State.STARTED.name())) {
			Long startTimestamp = logData.getTimestamp();
			Optional<LogData> logDta = getLogData(logData, logsData, State.FINISHED);
			if (logDta.isPresent()) {
				Long endTimestamp = logDta.get().getTimestamp();
				eventDuration = (double) (endTimestamp - startTimestamp);
			}
		} else if (logData.getState().equals(State.FINISHED.name())) {
			Long endTimestamp = logData.getTimestamp();
			Optional<LogData> logDta = getLogData(logData, logsData, State.STARTED);
			if (logDta.isPresent()) {
				Long startTimestamp = logDta.get().getTimestamp();
				eventDuration = (double) (endTimestamp - startTimestamp);
			}
		}
		return eventDuration;
	}

	private static Optional<LogData> getLogData(LogData logData, List<LogData> logsData, State state) {
		return logsData.stream()
				.filter(log -> log.getId().equals(logData.getId()) && log.getState().equals(state.name())).findFirst();
	}

}
