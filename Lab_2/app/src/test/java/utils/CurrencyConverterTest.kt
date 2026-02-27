package com.example.myapplication.utils

import org.junit.Assert.*
import org.junit.Test

class CurrencyConverterTest {

    //RUB -> USD
    //обычная конвертация
    @Test
    fun rubToUsd_normalConversion() {
        val converter = CurrencyConverter("RUB")
        val result = converter.rubToUsd(9050.0, 90.5)
        assertEquals(100.0, result, 0.001)
    }

    //конвертация 0 рублей
    @Test
    fun rubToUsd_zeroAmount() {
        val converter = CurrencyConverter("RUB")
        val result = converter.rubToUsd(0.0, 90.5)

        // 0 рублей = 0 долларов
        assertEquals(0.0, result, 0.001)
    }

    //конвертация отрицательного значения
    @Test(expected = IllegalArgumentException::class)
    fun rubToUsd_negativeAmount() {
        val converter = CurrencyConverter("RUB")
        converter.rubToUsd(-100.0, 90.5)
    }

    //некорректный курс 0
    @Test(expected = IllegalArgumentException::class)
    fun rubToUsd_invalidRate() {
        val converter = CurrencyConverter("RUB")
        converter.rubToUsd(100.0, 0.0)
    }

    //USD -> RUB

    //обычная конвертация
    @Test
    fun usdToRub_normalConversion() {
        val converter = CurrencyConverter("RUB")
        // 100 долларов = 9050 рублей
        val result = converter.usdToRub(100.0, 90.5)
        assertEquals(9050.0, result, 0.001)
    }

    //дробная сумма долларов
    @Test
    fun usdToRub_fractionalAmount() {
        val converter = CurrencyConverter("RUB")
        // 50.75 долларов * 90.5 = 4592.875 рублей
        val result = converter.usdToRub(50.75, 90.5)
        assertEquals(4592.875, result, 0.001)
    }

    //RUB -> EUR

    //обычная конвертация
    @Test
    fun rubToEur_normalConversion() {
        val converter = CurrencyConverter("RUB")
        // 9800 рублей / 98.0 = 100 евро
        val result = converter.rubToEur(9800.0, 98.0)
        assertEquals(100.0, result, 0.001)
    }

    //EUR -> RUB

    //обычная конвертация
    @Test
    fun eurToRub_normalConversion() {
        val converter = CurrencyConverter("RUB")
        // 100 евро * 98.0 = 9800 рублей
        val result = converter.eurToRub(100.0, 98.0)
        assertEquals(9800.0, result, 0.001)
    }

    // конвертация долларов в евро через рубли USD → RUB → EUR
    @Test
    fun usdToEur_crossRate() {
        val converter = CurrencyConverter("RUB")
        // 100 долларов
        // 1 USD = 90.5 RUB
        // 1 EUR = 98.0 RUB

        // 1 step: 100 USD * 90.5 = 9050 RUB
        // 2 step: 9050 RUB / 98.0 = 92.3469387755... EUR
        val result = converter.usdToEur(100.0, 90.5, 98.0)
        assertEquals(92.3469387755, result, 0.001)
    }

    //конвертация евро в доллары через рубли EUR → RUB → USD
    @Test
    fun eurToUsd_crossRate() {
        val converter = CurrencyConverter("RUB")
        // 100 евро
        // 1 USD = 90.5 RUB
        // 1 EUR = 98.0 RUB

        // 1 step: 100 EUR * 98.0 = 9800 RUB
        // 2 step: 9800 RUB / 90.5 = 108.2872928176... USD
        val result = converter.eurToUsd(100.0, 90.5, 98.0)
        assertEquals(108.2872928176, result, 0.001)
    }

    // Валидация

    //отрицательный курс при USD -> RUB

    @Test(expected = IllegalArgumentException::class)
    fun usdToRub_negativeRate() {
        val converter = CurrencyConverter("RUB")
        converter.usdToRub(100.0, -90.5)
    }

    // отрицательная сумма при RUB -> EUR
    @Test(expected = IllegalArgumentException::class)
    fun rubToEur_negativeAmount() {
        val converter = CurrencyConverter("RUB")
        converter.rubToEur(-500.0, 98.0)
    }
}