package kz.inspiredsamat.service.impl;

import static kz.inspiredsamat.entity.enums.State.REGISTERED;

import java.util.Arrays;
import java.util.List;
import kz.inspiredsamat.entity.Keyword;
import kz.inspiredsamat.entity.User;
import kz.inspiredsamat.service.KeywordService;
import kz.inspiredsamat.service.UserService;
import kz.inspiredsamat.service.handler.MessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Slf4j
@Component
@ToString
@RequiredArgsConstructor
public class SubscriptionHandler implements MessageHandler {

  private static final String NOT_REGISTERED_WARNING = """
      You have to be registered first. Click /start to restart the bot.
      """;

  private static final String SUBSCRIBE_MESSAGE = """
      Send me your keywords. For example:
      *Java, Backend, Spring*
      """;

  private static final String SUBSCRIBE_COMMAND = "/subscribe";

  private final UserService userService;
  private final KeywordService keywordService;

  @Override
  public SendMessage handle(Update update) {
    Message message = update.getMessage();
    User user = userService.getUserByChatId(message.getChatId()).orElse(null);

    SendMessage sendMessage = SendMessage.builder()
        .chatId(update.getMessage().getChatId())
        .text("")
        .build();

    if (user == null) {
      sendMessage.setText(NOT_REGISTERED_WARNING);
      return sendMessage;
    }

    if (message.isCommand()) {
      sendMessage.setText(SUBSCRIBE_MESSAGE);
      return sendMessage;
    }

    List<Keyword> keywords = Arrays.stream(message.getText().split(","))
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .distinct()
        .map(keyword -> Keyword.builder().keyword(keyword).build())
        .toList();

    List<Keyword> savedKeywords = keywordService.saveAll(keywords);
    user.getKeywords().addAll(savedKeywords);

    userService.save(user);
    return sendMessage;
  }

  @Override
  public <T> boolean supports(T value) {
    return value.equals(REGISTERED) || value.equals(SUBSCRIBE_COMMAND);
  }

  private void handleSubscribeCommand(SendMessage sendMessage) {
    List<String> keywords = getKeywords();

    List<InlineKeyboardButton> buttons = keywords.stream()
        .map(keyword -> InlineKeyboardButton.builder()
            .text(keyword)
            .callbackData("keyword")
            .build())
        .toList();

    InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder()
        .keyboardRow(buttons)
        .build();

    sendMessage.setParseMode("HTML");
    sendMessage.setText(SUBSCRIBE_MESSAGE);
    sendMessage.setReplyMarkup(keyboard);
  }

  private List<String> getKeywords() {
    return List.of("Spring Boot", "Java", "Docker", "Redis", "Kubernetes");
  }
}
