package kz.inspiredsamat.service;

import kz.inspiredsamat.config.BotConfiguration;
import kz.inspiredsamat.service.handler.UpdateHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
@SuppressWarnings("deprecation")
public class Bot extends TelegramLongPollingBot {

  private final BotConfiguration botConfiguration;
  private final UpdateHandler updateHandler;

  public Bot(BotConfiguration botConfiguration,
      TelegramBotsApi telegramBotsApi,
      UpdateHandler updateHandler) throws TelegramApiException {
    this.botConfiguration = botConfiguration;
    this.updateHandler = updateHandler;
    telegramBotsApi.registerBot(this);
  }

  @Override
  public String getBotUsername() {
    return botConfiguration.getUsername();
  }

  @Override
  public String getBotToken() {
    return botConfiguration.getToken();
  }

  @SneakyThrows
  @Override
  public void onUpdateReceived(Update update) {
    log.info("Update received: {}", update);
    SendMessage message = updateHandler.handle(update);
    execute(message);
  }
}
