package kz.inspiredsamat.telegram.service.handler;

import static kz.inspiredsamat.telegram.entity.enums.State.NOT_REGISTERED;

import java.util.List;
import java.util.Optional;
import kz.inspiredsamat.telegram.entity.User;
import kz.inspiredsamat.telegram.exception.UnsupportedCommandException;
import kz.inspiredsamat.telegram.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateHandler {

  private final List<MessageHandler> handlers;
  private final UserService userService;

  public SendMessage handle(Update update) {
    User user = getUser(update.getMessage().getChatId());
    log.info("Retrieved user from database {}", user);

    MessageHandler messageHandler;
    if (update.getMessage().isCommand()) {
      messageHandler = getHandlerByValue(update.getMessage().getText());
    } else {
      messageHandler = getHandlerByValue(user.getState());
    }

    log.info("Suitable handler found {}", messageHandler);
    return messageHandler.handle(update);
  }

  private User getUser(Long chatId) {
    Optional<User> userByChatId = userService.getUserByChatId(chatId);
    return userByChatId.orElseGet(() ->
        userService.save(User.builder()
            .id(chatId)
            .state(NOT_REGISTERED)
            .build())
    );
  }

  private <T> MessageHandler getHandlerByValue(T value) {
    return handlers.stream()
        .filter(handler -> handler.supports(value))
        .findAny()
        .orElseThrow(() -> new UnsupportedCommandException("Unsupported operation was provided"));
  }
}
