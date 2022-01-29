package com.credit.suisse.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;

import com.credit.suisse.dto.LogData;
import com.credit.suisse.dto.LogDataResponse;
import com.credit.suisse.entity.LogEntity;

@Mapper(componentModel = "spring")
public interface LogMapper {

	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	List<LogDataResponse> convertLogsEntityToLogsDataResponse(List<LogEntity> logsEntity);

	@Mappings({ @Mapping(source = "logData.id", target = "eventId"), @Mapping(source = "logData.type", target = "type"),
			@Mapping(source = "logData.host", target = "host"),
			@Mapping(source = "eventDuration", target = "eventDuration"), @Mapping(target = "id", ignore = true),
			@Mapping(target = "alert", expression = "java(eventDuration > 4 ? true : false)") })
	LogEntity convertLogDataToLogEntity(LogData logData, Double eventDuration);
}
