package main;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final String chatId = "641933485";

    public void sendNotification(String text) {
        SendMessage message = new SendMessage(chatId, text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Long chatId = update.getMessage().getChatId();
            System.out.println("🔍 Ваш chatId: " + chatId);

            SendMessage message = new SendMessage(String.valueOf(chatId), "✅ Вы успешно активировали бота. Теперь я знаю ваш chatId.");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String getBotUsername() {
        return "weathernotificationperdaybot";
    }

    @Override
    public String getBotToken() {
        return "8196905883:AAHcQp2Bm17oMPXviz1QZnHmbyqszAnQ9WU";
    }
}
