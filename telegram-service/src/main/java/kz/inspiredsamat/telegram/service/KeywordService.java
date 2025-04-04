package kz.inspiredsamat.telegram.service;

import java.util.List;
import kz.inspiredsamat.telegram.entity.Keyword;
import kz.inspiredsamat.telegram.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordService {

  private final KeywordRepository keywordRepository;

  public Keyword save(String keyword) {
    return keywordRepository.save(Keyword.builder()
        .keyword(keyword)
        .build()
    );
  }

  public List<Keyword> saveAll(List<Keyword> keywords) {
    return keywordRepository.saveAll(keywords);
  }
}
