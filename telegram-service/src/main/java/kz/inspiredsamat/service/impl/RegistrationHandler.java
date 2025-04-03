package kz.inspiredsamat.service.impl;

import static kz.inspiredsamat.entity.enums.State.MISSING_LASTNAME;
import static kz.inspiredsamat.entity.enums.State.NOT_REGISTERED;
import static kz.inspiredsamat.entity.enums.State.REGISTERED;

import kz.inspiredsamat.entity.User;
import kz.inspiredsamat.exception.UserNotFoundException;
import kz.inspiredsamat.service.UserService;
import kz.inspiredsamat.service.handler.MessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@RequiredArgsConstructor
@ToString
public class RegistrationHandler implements MessageHandler {

  private static final String WELCOME_MESSAGE = """
      👋 Welcome! I am a job vacancy bot.
      I can send you job postings based on your subscribed keywords.
      
      🔍 I will notify you whenever new matching job vacancies appear!
      
      🛠 Available commands:
      /start - Restart the bot
      /subscribe - Subscribe to job alerts
      /unsubscribe - Remove your subscription
      """;

  private static final String USER_NAME_REGISTRATION_MESSAGE = """
      🚀 Let's get started!
      Firstly, let's get to know each other! What's your name?
      """;

  private static final String USER_LASTNAME_REGISTRATION_MESSAGE = """
      What's your lastname?
      """;

  private static final String REGISTERED_ALREADY = """
      You are already registered. Click /subscribe to add subscriptions.
      """;

  private static final String TRY_AGAIN = """
      System error. Please, try again!
      """;

  private static final String START_COMMAND = "/start";

  private final UserService userService;

  @Override
  public SendMessage handle(Update update) {
    Message message = update.getMessage();
    User user = userService.getUserByChatId(message.getChatId())
        .orElseThrow(() -> new UserNotFoundException(
            String.format("User with id %d not found. Clearly missed some steps.",
                message.getChatId()))
        );

    if (!update.getMessage().isCommand()) {
      if (user.getName() == null) {
        user.setName(update.getMessage().getText());
        user.setState(MISSING_LASTNAME);
      } else if (user.getLastname() == null) {
        user.setLastname(update.getMessage().getText());
        user.setState(REGISTERED);
      } else {
        log.info("All user fields are filled. Unknown text: {}", update.getMessage().getText());
      }
    }

    SendMessage sendMessage = SendMessage.builder()
        .chatId(message.getChatId())
        .text("")
        .build();

    switch (user.getState()) {
      case NOT_REGISTERED -> sendMessage.setText(USER_NAME_REGISTRATION_MESSAGE);
      case MISSING_LASTNAME -> sendMessage.setText(USER_LASTNAME_REGISTRATION_MESSAGE);
      case REGISTERED -> sendMessage.setText(REGISTERED_ALREADY);
      default -> sendMessage.setText(TRY_AGAIN);
    }

    userService.save(user);
    return sendMessage;
  }

  @Override
  public <T> boolean supports(T value) {
    return value.equals(NOT_REGISTERED) || value.equals(MISSING_LASTNAME) || value.equals(
        START_COMMAND);
  }
}
