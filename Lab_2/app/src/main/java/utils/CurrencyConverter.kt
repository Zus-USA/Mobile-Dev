package com.example.myapplication.utils

// Класс для конвертации
data class CurrencyConverter(
    val baseCurrency: String
) {
    //RUB -> USD
    fun rubToUsd(amountRub: Double, rubToUsdRate: Double): Double {
        // сумма не отрицательная
        require(amountRub >= 0) { "Сумма не может быть отрицательной" }

        //  курс положительный
        require(rubToUsdRate > 0) { "Курс должен быть положительным" }

        // Конвертация
        // 9050 рублей / 90.5 = 100 долларов
        return amountRub / rubToUsdRate
    }

    //USD -> RUB
    fun usdToRub(amountUsd: Double, rubToUsdRate: Double): Double {
        require(amountUsd >= 0) { "Сумма не может быть отрицательной" }
        require(rubToUsdRate > 0) { "Курс должен быть положительным" }

        // Конвертация
        // 100 долларов * 90.5 = 9050 рублей
        return amountUsd * rubToUsdRate
    }

    //RUB -> EUR
    fun rubToEur(amountRub: Double, rubToEurRate: Double): Double {
        require(amountRub >= 0) { "Сумма не может быть отрицательной" }
        require(rubToEurRate > 0) { "Курс должен быть положительным" }

        // рубли / курс евро
        return amountRub / rubToEurRate
    }

    //EUR -> RUB
    fun eurToRub(amountEur: Double, rubToEurRate: Double): Double {
        require(amountEur >= 0) { "Сумма не может быть отрицательной" }
        require(rubToEurRate > 0) { "Курс должен быть положительным" }

        //евро * курс
        return amountEur * rubToEurRate
    }

    //USD → RUB → EUR
    fun usdToEur(amountUsd: Double, rubToUsdRate: Double, rubToEurRate: Double): Double {
        // доллары в рубли
        val amountInRub = usdToRub(amountUsd, rubToUsdRate)

        //  рубли в евро
        return rubToEur(amountInRub, rubToEurRate)
    }

    //EUR → RUB → USD
    fun eurToUsd(amountEur: Double, rubToUsdRate: Double, rubToEurRate: Double): Double {
        // евро в рубли
        val amountInRub = eurToRub(amountEur, rubToEurRate)

        // рубли в доллары
        return rubToUsd(amountInRub, rubToUsdRate)
    }
}