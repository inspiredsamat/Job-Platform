package kz.inspiredsamat.jobpublish.mapper;

import kz.inspiredsamat.jobpublish.dto.JobResponseDto;
import kz.inspiredsamat.jobpublish.entity.Job;
import kz.inspiredsamat.jobpublish.dto.JobCreateRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface JobMapper {

  Job toEntity(JobCreateRequestDto jobCreateRequestDto);

  JobCreateRequestDto toJobCreateRequestDto(Job job);

  void updateWithNull(JobCreateRequestDto jobCreateRequestDto, @MappingTarget Job job);

  Job toEntity(JobResponseDto jobResponseDto);

  JobResponseDto toJobResponseDto(Job job);
}