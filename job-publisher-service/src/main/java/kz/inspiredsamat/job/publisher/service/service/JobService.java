package kz.inspiredsamat.job.publisher.service.service;

import kz.inspiredsamat.job.publisher.service.dto.JobCreateRequestDto;
import kz.inspiredsamat.job.publisher.service.entity.Job;
import kz.inspiredsamat.job.publisher.service.exception.ResourceNotFoundException;
import kz.inspiredsamat.job.publisher.service.mapper.JobMapper;
import kz.inspiredsamat.job.publisher.service.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {

  private final JobRepository jobRepository;
  private final JobMapper jobMapper;

  public Page<Job> findAll(Pageable pageable) {
    return jobRepository.findAll(pageable);
  }


  public Job findById(Long id) {
    return jobRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format("Job with id %s not found", id)));
  }

  public Job save(JobCreateRequestDto jobCreateRequestDto) {
    return jobRepository.save(jobMapper.toEntity(jobCreateRequestDto));
  }

  public void deleteById(Long id) {
    jobRepository.deleteById(id);
  }
}
