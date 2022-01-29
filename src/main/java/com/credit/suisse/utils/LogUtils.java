package com.credit.suisse.utils;

import java.util.List;
import java.util.Objects;
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
		State state = State.getStateName(logData.getState());
		if (Objects.nonNull(state)) {
			Long timeStamp = logData.getTimestamp();
			Optional<LogData> logDta = org.apache.commons.collections4.CollectionUtils.emptyIfNull(logsData).stream()
					.filter(log -> log.getId().equals(logData.getId()) && log.getState().equals(state.name()))
					.findFirst();
			if (logDta.isPresent()) {
				Long difference = logDta.get().getTimestamp();
				return (double) (timeStamp - difference);
			}
		}

		return null;
	}

}
