package kz.inspiredsamat.service;

import java.util.Optional;
import kz.inspiredsamat.entity.User;
import kz.inspiredsamat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public boolean existsById(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("Chat id can not be null");
    }

    return userRepository.existsById(id);
  }

  public Optional<User> getUserByChatId(Long chatId) {
    return userRepository.findById(chatId);
  }

  public User save(User user) {
    return userRepository.save(user);
  }
}
