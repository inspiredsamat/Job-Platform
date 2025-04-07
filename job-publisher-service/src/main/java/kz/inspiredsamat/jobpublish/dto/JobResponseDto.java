package kz.inspiredsamat.jobpublish.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link kz.inspiredsamat.jobpublish.entity.Job}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record JobResponseDto(
    String title,
    String company,
    String description,
    String location,
    List<List<String>> keywords,
    LocalDateTime postedAt) {

}