package com.ditec.pelada.sorteioequipe.util;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class MatchDayUtils {

    public static OffsetDateTime getMatchDay() {
        // Obtém a data e hora atual com fuso horário
        OffsetDateTime currentDateTime = OffsetDateTime.now(ZoneOffset.UTC);

        // Obtém o dia da semana
        DayOfWeek dayOfWeek = currentDateTime.getDayOfWeek();

        // Imprime o dia da semana
        System.out.println("Dia da semana atual: " + dayOfWeek);
        return getMatchDay(dayOfWeek);
    }

    private static OffsetDateTime getMatchDay(DayOfWeek weekDay) {
        OffsetDateTime matchDay = OffsetDateTime.now().withHour(19).withMinute(30).withSecond(00);
        switch (weekDay) {
            case SUNDAY -> {
                return matchDay.plusDays(3);
            }
            case MONDAY -> {
                return matchDay.plusDays(2);
            }
            case TUESDAY -> {
                return matchDay.plusDays(1);
            }
            case THURSDAY -> {
                return matchDay.plusDays(6);
            }
            case FRIDAY -> {
                return matchDay.plusDays(5);
            }
            case SATURDAY -> {
                return matchDay.plusDays(4);
            }
            default -> {
                return matchDay;
            }
        }
    }
}
