package kz.inspiredsamat.controller;

import kz.inspiredsamat.service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RestController
@RequiredArgsConstructor
public class TelegramController {

    private final TelegramService telegramService;

    @GetMapping("/test")
    public void test() throws TelegramApiException {

    }
}
