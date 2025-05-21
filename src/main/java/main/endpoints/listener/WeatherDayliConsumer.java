package main.endpoints.listener;

import main.TelegramBot;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class WeatherDayliConsumer {
    private final TelegramBot telegramBot;
    public WeatherDayliConsumer(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @RabbitListener(queues = "avarage-temp-queue")
    public void receiveMessage(Object message) {
        System.out.println("Получено из RabbitMQ: " + message);
        telegramBot.sendNotification("Уведомление:\n" + message);
    }
}
