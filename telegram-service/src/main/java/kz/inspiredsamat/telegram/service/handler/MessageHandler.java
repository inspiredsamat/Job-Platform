package kz.inspiredsamat.telegram.service.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageHandler {

  SendMessage handle(Update update);

  <T> boolean supports(T value);
}
