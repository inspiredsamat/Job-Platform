package kz.inspiredsamat.telegram.repository;

import kz.inspiredsamat.telegram.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

}