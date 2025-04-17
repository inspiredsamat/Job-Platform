package kz.inspiredsamat.job.publisher.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link kz.inspiredsamat.job.publisher.service.entity.Job}
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public record JobCreateRequestDto(
    @NotBlank String title,
    @NotBlank(message = "Company name should be provided") String company,
    @NotBlank(message = "Description should be provided") String description,
    @NotBlank(message = "Location should be provided") String location,
    @NotNull(message = "Keywords should be provided") List<String> keywords,
    LocalDateTime postedAt) {

}