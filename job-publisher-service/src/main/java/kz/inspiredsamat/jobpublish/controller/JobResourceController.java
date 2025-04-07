package kz.inspiredsamat.jobpublish.controller;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import kz.inspiredsamat.jobpublish.dto.JobCreateRequestDto;
import kz.inspiredsamat.jobpublish.dto.JobResponseDto;
import kz.inspiredsamat.jobpublish.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/rest/jobs")
@RequiredArgsConstructor
public class JobResourceController {

  private final JobService jobService;

  @GetMapping
  public Page<JobResponseDto> getAll(Pageable pageable) {
    return jobService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public JobResponseDto getOne(@PathVariable Long id) {
    return jobService.findById(id);
  }

  @PostMapping
  public JobCreateRequestDto create(@RequestBody @Valid JobCreateRequestDto dto) {
    return jobService.save(dto);
  }

  @PatchMapping("/{id}")
  public JobCreateRequestDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode)
      throws IOException {
    return jobService.update(id, patchNode);
  }

  @DeleteMapping("/{id}")
  public JobCreateRequestDto delete(@PathVariable Long id) {
    return jobService.deleteById(id);
  }

  @DeleteMapping
  public void deleteMany(@RequestParam List<Long> ids) {
    jobService.deleteManyByIds(ids);
  }
}
