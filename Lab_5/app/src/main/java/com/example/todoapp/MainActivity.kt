package com.example.todoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var counter = 0
    private val tasks = mutableListOf<String>()

    //Инд №2 Счетчик количества задач
    private var taskCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Инициализация

        //Счётчик
        val textCounter = findViewById<TextView>(R.id.textCounter)
        val buttonIncrement = findViewById<Button>(R.id.buttonIncrement)
        val buttonReset = findViewById<Button>(R.id.buttonReset)

        //Ввод текста
        val editTextInput = findViewById<EditText>(R.id.editTextInput)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val textEntered = findViewById<TextView>(R.id.textEntered)

        //ToDо список
        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val buttonAddTask = findViewById<Button>(R.id.buttonAddTask)
        val buttonRemoveLast = findViewById<Button>(R.id.buttonRemoveLast)
        val textTasks = findViewById<TextView>(R.id.textTasks)

        //Инд №2 Счетчик задач
        val textTaskCount = findViewById<TextView>(R.id.textTaskCount)


        //Счётчик нажатий
        updateCounterDisplay(textCounter)

        buttonIncrement.setOnClickListener {
            counter++
            updateCounterDisplay(textCounter)
        }

        // Сброс счётчика
        buttonReset.setOnClickListener {
            counter = 0
            updateCounterDisplay(textCounter)
            Toast.makeText(this, R.string.toast_counter_reset, Toast.LENGTH_SHORT).show()
        }

        // Отображение введенного текста
        buttonShow.setOnClickListener {
            val inputText = editTextInput.text.toString()
            textEntered.text = getString(R.string.label_entered_with_text, inputText)
        }

        // ToDо список
        buttonAddTask.setOnClickListener {
            val task = editTextTask.text.toString()
            if (task.isNotBlank()) {
                tasks.add(task)
                // Инд №2 Увеличить счетчик задач
                taskCount++
                updateTaskCountDisplay(textTaskCount)
                updateTasksDisplay(textTasks)
                editTextTask.text.clear()
            } else {
                Toast.makeText(this, R.string.toast_empty_task, Toast.LENGTH_SHORT).show()
            }
        }

        // Удаление последней задачи
        buttonRemoveLast.setOnClickListener {
            if (tasks.isNotEmpty()) {
                tasks.removeAt(tasks.lastIndex)
                // Инд №2 Уменьшить счетчик задач
                taskCount--
                updateTaskCountDisplay(textTaskCount)
                updateTasksDisplay(textTasks)
                Toast.makeText(this, R.string.toast_task_removed, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, R.string.toast_list_empty, Toast.LENGTH_SHORT).show()
            }
        }

        updateTaskCountDisplay(textTaskCount)
    }

    private fun updateCounterDisplay(textView: TextView) {
        textView.text = getString(R.string.counter_text, counter)
    }

    private fun updateTasksDisplay(textView: TextView) {
        if (tasks.isEmpty()) {
            textView.text = getString(R.string.label_tasks)
        } else {
            textView.text = tasks.joinToString("\n") { "• $it" }
        }
    }

    //Инд №2 обновление счетчика задач
    private fun updateTaskCountDisplay(textView: TextView) {
        textView.text = getString(R.string.task_count_label, taskCount)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", counter)
        outState.putStringArrayList("tasks", ArrayList(tasks))
        outState.putInt("taskCount", taskCount)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        counter = savedInstanceState.getInt("counter")
        tasks.clear()
        tasks.addAll(savedInstanceState.getStringArrayList("tasks") ?: emptyList())
        taskCount = savedInstanceState.getInt("taskCount")
        val textCounter = findViewById<TextView>(R.id.textCounter)
        val textTasks = findViewById<TextView>(R.id.textTasks)
        val textTaskCount = findViewById<TextView>(R.id.textTaskCount)
        updateCounterDisplay(textCounter)
        updateTasksDisplay(textTasks)
        updateTaskCountDisplay(textTaskCount)
    }
}