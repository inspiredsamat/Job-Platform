package kz.inspiredsamat.job.publisher.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link kz.inspiredsamat.job.publisher.service.entity.Job}
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public record JobResponseDto(
    Long id,
    String title, String company, String description,
    String location,
    List<String> keywords,
    LocalDateTime postedAt
) {

}