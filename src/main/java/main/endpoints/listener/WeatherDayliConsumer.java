package main.endpoints.listener;

import main.TelegramBot;
import main.dto.DayliWeatherSummaryDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class WeatherDayliConsumer {
    private final TelegramBot telegramBot;
    public WeatherDayliConsumer(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @RabbitListener(queues = "avarage-temp-queue")
    public void receiveMessage(DayliWeatherSummaryDto dto) {
        System.out.println("Получено из RabbitMQ: " + dto);

        String messageForTelegram = "Температура в городе " + dto.cityName()
                + "в среднем за сегодня была "+ dto.avgTemp() + " Максимальная температура: "
                + dto.minTemp() + "максимальная температура: " + dto.maxTemp();

        telegramBot.sendNotification("Уведомление:\n" + messageForTelegram);
    }
}
