package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// Импорт классов из utils
import com.example.myapplication.utils.Book
import com.example.myapplication.utils.formatAuthorName
import com.example.myapplication.utils.applyDiscount
import com.example.myapplication.utils.CurrencyConverter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val book = Book(
            title = "Война и мир",
            author = "Толстой Лев Николаевич",
            year = 1869,
            price = 500.0
        )

        // Форматирование имени автора
        val formattedAuthor = formatAuthorName(book.author)

        // Расчет цены со скидкой 15%
        val discountedPrice = applyDiscount(book.price, 15.0)

        findViewById<TextView>(R.id.textView1).text = getString(R.string.book_title, book.title)
        findViewById<TextView>(R.id.textView2).text = getString(R.string.book_author, formattedAuthor)
        findViewById<TextView>(R.id.textView3).text = getString(R.string.book_price, discountedPrice, getString(R.string.suffix_rub))

        // конвертер
        val converter = CurrencyConverter("RUB")

        // Исходная сумма
        val rubAmount = 1000.0

        // Курсы валют
        val usdRate = 90.5
        val eurRate = 98.0

        // Выполнение конвертации
        val usdResult = converter.rubToUsd(rubAmount, usdRate)
        val eurResult = converter.rubToEur(rubAmount, eurRate)

        // заголовк
        findViewById<TextView>(R.id.textView4).text = getString(R.string.converter_title)

        // сумма в рублях
        findViewById<TextView>(R.id.textView5).text = getString(R.string.currency_rub_line, rubAmount.toInt(), getString(R.string.suffix_rub))

        // результат в USD
        findViewById<TextView>(R.id.textView6).text = getString(R.string.currency_usd_line, usdResult, getString(R.string.suffix_usd))

        // результат в EUR
        findViewById<TextView>(R.id.textView7).text = getString(R.string.currency_eur_line, eurResult, getString(R.string.suffix_eur))
    }
}