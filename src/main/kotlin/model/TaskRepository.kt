import com.example.model.Priority
import com.example.model.Task

object TaskRepository {
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

    fun allTasks(): List<Task> = tasks.values.toList()

    fun tasksByPriority(priority: Priority) = tasks.values.filter {
        it.priority == priority
    }

    fun taskByName(name: String) = tasks[name]

    fun addTask(task: Task) {
        if (tasks.containsKey(task.name))
            throw IllegalStateException("Cannot duplicate task names!")
        tasks[task.name] = task
    }

    fun removeTask (name: String): Boolean{
        return tasks.remove(name) != null
    }
}
