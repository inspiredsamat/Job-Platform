package kz.inspiredsamat.jobpublish.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import kz.inspiredsamat.jobpublish.entity.Job;

/**
 * DTO for {@link Job}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record JobCreateRequestDto(
    @NotEmpty(message = "Title can not be empty") String title,
    @NotEmpty(message = "Company name can not be empty") String company,
    @NotEmpty(message = "Job description can not be empty") String description,
    @NotEmpty(message = "Job location can not be empty") String location,
    @NotNull(message = "Job keywords should be provided") List<List<String>> keywords) {

}