package edu.goit.telegrambot.bank;

import edu.goit.telegrambot.currency.Currency;
import edu.goit.telegrambot.currency.CurrencyType;


import java.math.RoundingMode;
import java.util.List;

public abstract class Bank {
    String name;
    List<Currency> currencies;

    abstract public void updateRate();

    public String getCurrencyRate(CurrencyType curType, byte tolerance) {
        return String.format("Курс валют %s/%s:\n", curType.name(), CurrencyType.UAH) +
                "Купівля: " + currencies.stream()
                .filter(item -> item.getType().equals(curType))
                .map(it -> it.getBuyRate().setScale(tolerance, RoundingMode.HALF_UP))
                .findFirst().orElseThrow() +
                "\nПродаж: " + currencies.stream()
                .filter(item -> item.getType().equals(curType))
                .map(it -> it.getSellRate().setScale(tolerance, RoundingMode.HALF_UP))
                .findFirst()
                .orElseThrow();
    }

    public String getName() {
        return name;
    }
}
