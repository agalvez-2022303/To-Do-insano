package com.albertogalvez.ProgramaConIA.service;

import com.albertogalvez.ProgramaConIA.model.Task;
import com.albertogalvez.ProgramaConIA.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Task> getPendingTasks() {
        return taskRepository.findByCompletedOrderByCreatedAtDesc(false);
    }

    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompletedOrderByCreatedAtDesc(true);
    }

    @Transactional
    public Task createTask(Task task) {
        task.setCompleted(false);
        task.setCompletedAt(null);
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());

        return taskRepository.save(task);
    }

    @Transactional
    public Task toggleTaskStatus(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        task.setCompleted(!task.isCompleted());
        task.setCompletedAt(task.isCompleted() ? LocalDateTime.now() : null);

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public void deleteCompletedTasks() {
        List<Task> completedTasks = getCompletedTasks();
        taskRepository.deleteAll(completedTasks);
    }
}