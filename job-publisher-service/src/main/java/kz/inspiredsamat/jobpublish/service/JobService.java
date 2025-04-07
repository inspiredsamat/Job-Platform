package kz.inspiredsamat.jobpublish.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import kz.inspiredsamat.jobpublish.dto.JobCreateRequestDto;
import kz.inspiredsamat.jobpublish.dto.JobResponseDto;
import kz.inspiredsamat.jobpublish.entity.Job;
import kz.inspiredsamat.jobpublish.mapper.JobMapper;
import kz.inspiredsamat.jobpublish.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class JobService {

  private final JobRepository jobRepository;
  private final JobMapper jobMapper;
  private final ObjectMapper objectMapper;

  public Page<JobResponseDto> findAll(Pageable pageable) {
    Page<Job> jobs = jobRepository.findAll(pageable);
    return jobs.map(jobMapper::toJobResponseDto);
  }

  public JobResponseDto findById(Long id) {
    Optional<Job> jobOptional = jobRepository.findById(id);
    return jobMapper.toJobResponseDto(jobOptional.orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Entity with id `%s` not found".formatted(id))));
  }


  public JobCreateRequestDto save(@Valid JobCreateRequestDto dto) {
    Job job = jobMapper.toEntity(dto);
    Job resultJob = jobRepository.save(job);
    return jobMapper.toJobCreateRequestDto(resultJob);
  }

  public JobCreateRequestDto update(Long id, JsonNode patchNode) throws IOException {
    Job job = jobRepository.findById(id).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Entity with id `%s` not found".formatted(id)));

    JobCreateRequestDto jobCreateRequestDto = jobMapper.toJobCreateRequestDto(job);
    objectMapper.readerForUpdating(jobCreateRequestDto).readValue(patchNode);
    jobMapper.updateWithNull(jobCreateRequestDto, job);

    Job resultJob = jobRepository.save(job);
    return jobMapper.toJobCreateRequestDto(resultJob);
  }

  public JobCreateRequestDto deleteById(Long id) {
    Job job = jobRepository.findById(id).orElse(null);
    if (job != null) {
      jobRepository.delete(job);
    }
    return jobMapper.toJobCreateRequestDto(job);
  }

  public void deleteManyByIds(List<Long> ids) {
    jobRepository.deleteAllById(ids);
  }
}
