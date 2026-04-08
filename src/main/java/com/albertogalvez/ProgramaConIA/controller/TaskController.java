package com.albertogalvez.ProgramaConIA.controller;

import com.albertogalvez.ProgramaConIA.model.Task;
import com.albertogalvez.ProgramaConIA.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("=== Entrando al método index ===");

        // Agrega datos de prueba si no hay tareas
        if (taskService.getAllTasks().isEmpty()) {
            System.out.println("Creando tarea de prueba...");
            Task testTask = new Task();
            testTask.setTitle("Tarea de prueba");
            testTask.setDescription("Esta es una tarea de prueba");
            taskService.createTask(testTask);
        }

        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("pendingTasks", taskService.getPendingTasks());
        model.addAttribute("completedTasks", taskService.getCompletedTasks());
        model.addAttribute("newTask", new Task());

        System.out.println("Tareas pendientes: " + taskService.getPendingTasks().size());
        System.out.println("Tareas completadas: " + taskService.getCompletedTasks().size());

        return "index";
    }

    @PostMapping("/tasks")
    public String createTask(@ModelAttribute Task task) {
        System.out.println("Creando tarea: " + task.getTitle());
        taskService.createTask(task);
        return "redirect:/";
    }

    @GetMapping("/tasks/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        System.out.println("Toggle tarea: " + id);
        taskService.toggleTaskStatus(id);
        return "redirect:/";
    }

    @GetMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        System.out.println("Eliminando tarea: " + id);
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @GetMapping("/tasks/delete-completed")
    public String deleteCompletedTasks() {
        System.out.println("Eliminando tareas completadas");
        taskService.deleteCompletedTasks();
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/update")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        System.out.println("Actualizando tarea: " + id);
        taskService.updateTask(id, task);
        return "redirect:/";
    }
}