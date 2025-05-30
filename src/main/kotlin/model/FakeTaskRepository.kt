package com.example.model

import TaskRepository

class FakeTaskRepository : TaskRepository {
    private val tasks = mutableMapOf<String, Task>(

    )

    init {
        listOf(
            Task("cleaning", "Clean the house", Priority.Low),
            Task("gardening", "Mow the lawn", Priority.Medium),
            Task("shopping", "Buy the groceries", Priority.High),
            Task("painting", "Paint the fence", Priority.Medium)
        ).forEach { task ->
            tasks[task.name] = task
        }
    }

    override suspend fun allTasks(): List<Task> = tasks.values.toList()

    override suspend fun tasksByPriority(priority: Priority) = tasks.values.filter {
        it.priority == priority
    }

    override suspend fun taskByName(name: String) = tasks[name]

    override suspend fun addTask(task: Task) {
        if (tasks.containsKey(task.name))
            throw IllegalStateException("Cannot duplicate task names!")
        tasks[task.name] = task
    }

    override suspend fun removeTask (name: String): Boolean{
        return tasks.remove(name) != null
    }
}
