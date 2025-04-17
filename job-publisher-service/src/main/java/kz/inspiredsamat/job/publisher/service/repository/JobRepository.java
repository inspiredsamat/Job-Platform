package kz.inspiredsamat.job.publisher.service.repository;

import kz.inspiredsamat.job.publisher.service.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}