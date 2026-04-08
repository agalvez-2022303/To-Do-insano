package com.albertogalvez.ProgramaConIA.repository;

import com.albertogalvez.ProgramaConIA.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompletedOrderByCreatedAtDesc(boolean completed);
    List<Task> findAllByOrderByCreatedAtDesc();
}