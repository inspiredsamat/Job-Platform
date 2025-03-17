package kz.inspiredsamat.service;

import kz.inspiredsamat.config.BotConfiguration;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private static final String WELCOME_MESSAGE = """
           👋 Welcome! I am a job vacancy bot.
           I can send you job postings based on your subscribed keywords.
           \s
           🔍 I will notify you whenever new matching job vacancies appear!
           \s
           🛠 Available commands:
           /start - Restart the bot
           /subscribe - Subscribe to job alerts
           /unsubscribe - Remove your subscription
           /help - Get instructions
           \s
           🚀 Let's get started! Send me your keywords. For example:
           *Java, Backend, Spring*
           """;

    private InlineKeyboardMarkup keyboard;

    private final BotConfiguration botConfiguration;

    public TelegramBot(TelegramBotsApi telegramBotsApi,
                       BotConfiguration botConfiguration) throws TelegramApiException {
        this.botConfiguration = botConfiguration;
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
        var next = InlineKeyboardButton.builder()
                .text("Hello").callbackData("next")
                .build();
        keyboard = InlineKeyboardMarkup.builder()
                        .keyboardRow(List.of(next))
                                .build();

        log.info("Update received: {}", update.getMessage().getText());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setParseMode("HTML");
        sendMessage.setText(WELCOME_MESSAGE);
        sendMessage.setReplyMarkup(keyboard);
        super.execute(sendMessage);
    }
}
