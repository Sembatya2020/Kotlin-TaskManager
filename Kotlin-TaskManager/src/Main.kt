//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// Data class for representing a task
// Includes a completed flag for future features

data class Task(val id: Int, var description: String, var completed: Boolean = false)

// TaskManager class handles all task operations
class TaskManager {
    private val tasks = mutableListOf<Task>() // Mutable collection
    private var nextId = 1 // Mutable variable
    val appName: String = "Kotlin Task Manager" // Immutable variable

    // Add a new task
    fun addTask(description: String) {
        tasks.add(Task(nextId++, description))
        println("Task added successfully.")
    }

    // List all tasks with their status
    fun listTasks() {
        if (tasks.isEmpty()) {
            println("No tasks found.")
        } else {
            println("\nYour Tasks:")
            tasks.forEach { println("${it.id}: ${it.description} [${if (it.completed) "Completed" else "Pending"}]") }
        }
    }

    // Remove a task by ID
    fun removeTask(id: Int?) {
        if (id == null) {
            println("Invalid ID.")
            return
        }
        if (tasks.removeIf { it.id == id }) {
            println("Task removed.")
        } else {
            println("Task not found.")
        }
    }

    // Edit a task's description
    fun editTask(id: Int?, newDesc: String) {
        val task = tasks.find { it.id == id }
        if (task != null) {
            task.description = newDesc
            println("Task updated.")
        } else {
            println("Task not found.")
        }
    }

    // Mark a task as completed
    fun completeTask(id: Int?) {
        val task = tasks.find { it.id == id }
        if (task != null) {
            task.completed = true
            println("Task marked as completed.")
        } else {
            println("Task not found.")
        }
    }

    // Search for tasks by keyword
    fun searchTasks(keyword: String) {
        val found = tasks.filter { it.description.contains(keyword, ignoreCase = true) }
        if (found.isEmpty()) {
            println("No tasks match your search.")
        } else {
            println("\nSearch Results:")
            found.forEach { println("${it.id}: ${it.description} [${if (it.completed) "Completed" else "Pending"}]") }
        }
    }

    // Show statistics about tasks
    fun showStats() {
        val total = tasks.size
        val completed = tasks.count { it.completed }
        val pending = total - completed
        println("Total tasks: $total, Completed: $completed, Pending: $pending")
    }
}

// Main function: entry point for the app
fun main() {
    val manager = TaskManager()
    var running = true // Mutable variable

    // Main loop for user interaction
    while (running) { // Loop demonstration
        println("\n--- ${manager.appName} ---")
        println("1. Add Task")
        println("2. List Tasks")
        println("3. Remove Task")
        println("4. Edit Task")
        println("5. Complete Task")
        println("6. Search Tasks")
        println("7. Show Stats")
        println("8. Exit")
        print("Choose an option: ")

        val option = readln().toIntOrNull() // Expression and conditional
        // when keyword demonstration with more branches
        when (option) {
            1 -> {
                print("Enter task description: ")
                val desc = readln()
                manager.addTask(desc)
            }
            2 -> manager.listTasks()
            3 -> {
                print("Enter task ID to remove: ")
                val id = readln().toIntOrNull()
                manager.removeTask(id)
            }
            4 -> {
                print("Enter task ID to edit: ")
                val id = readln().toIntOrNull()
                print("Enter new description: ")
                val newDesc = readln()
                manager.editTask(id, newDesc)
            }
            5 -> {
                print("Enter task ID to mark as completed: ")
                val id = readln().toIntOrNull()
                manager.completeTask(id)
            }
            6 -> {
                print("Enter keyword to search: ")
                val keyword = readln()
                manager.searchTasks(keyword)
            }
            7 -> manager.showStats()
            8 -> {
                println("Goodbye!")
                running = false
            }
            null -> println("Please enter a valid number.")
            else -> println("Invalid option, please try again.")
        }
    }
}
// End of program. All requirements and comments included.
