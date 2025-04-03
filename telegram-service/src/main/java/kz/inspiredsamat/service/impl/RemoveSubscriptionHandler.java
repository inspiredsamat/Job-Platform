package kz.inspiredsamat.service.impl;

import static kz.inspiredsamat.entity.enums.State.SUBSCRIBED;

import kz.inspiredsamat.service.UserService;
import kz.inspiredsamat.service.handler.MessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@ToString
@Component
@RequiredArgsConstructor
public class RemoveSubscriptionHandler implements MessageHandler {

  private static final String UNSUBSCRIBE_MESSAGE = """
      Your subscriptions were successfully removed👌🏻
      """;

  private static final String UNSUBSCRIBE_COMMAND = "/unsubscribe";

  private final UserService userService;

  @Override
  public SendMessage handle(Update update) {
    log.info("Remove subscription handle");
    return null;
  }

  @Override
  public <T> boolean supports(T value) {
    return value.equals(SUBSCRIBED) || value.equals(UNSUBSCRIBE_COMMAND);
  }
}
