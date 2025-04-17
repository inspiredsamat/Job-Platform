package kz.inspiredsamat.job.publisher.service.controller;

import kz.inspiredsamat.job.publisher.service.dto.JobCreateRequestDto;
import kz.inspiredsamat.job.publisher.service.entity.Job;
import kz.inspiredsamat.job.publisher.service.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobRestController {

  private final JobService jobService;

  @GetMapping
  public Page<Job> getAll(Pageable pageable) {
    return jobService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public Job getOne(@PathVariable Long id) {
    return jobService.findById(id);
  }

  @PostMapping
  public Job create(@RequestBody JobCreateRequestDto jobCreateRequestDto) {
    return jobService.save(jobCreateRequestDto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    jobService.deleteById(id);
  }
}
