package kz.inspiredsamat.jobpublish.repository;

import kz.inspiredsamat.jobpublish.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}