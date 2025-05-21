package main.dto;

public record DayliWeatherSummaryDto(
        String cityName,
        Double minTemp,
        Double maxTemp,
        Double avgTemp
) {
}
