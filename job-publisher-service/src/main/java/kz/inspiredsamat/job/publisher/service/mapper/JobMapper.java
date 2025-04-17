package kz.inspiredsamat.job.publisher.service.mapper;

import kz.inspiredsamat.job.publisher.service.dto.JobCreateRequestDto;
import kz.inspiredsamat.job.publisher.service.dto.JobResponseDto;
import kz.inspiredsamat.job.publisher.service.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface JobMapper {

  Job toEntity(JobCreateRequestDto jobCreateRequestDto);

  JobCreateRequestDto toJobCreateRequestDto(Job job);

  JobResponseDto toJobResponseDto(Job job);
}