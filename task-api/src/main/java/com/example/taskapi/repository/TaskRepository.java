package com.example.taskapi.repository;

import com.example.taskapi.model.Task;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;

    public List<Task> findAll() {
        return tasks;
    }

    public Optional<Task> findById(Long id) {
        return tasks.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public Task save(Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return task;
    }

    public void update(Long id, Task updatedTask) {
        findById(id).ifPresent(t -> {
            t.setTitle(updatedTask.getTitle());
            t.setDescription(updatedTask.getDescription());
            t.setDueDate(updatedTask.getDueDate());
        });
    }

    public void delete(Long id) {
        tasks.removeIf(t -> t.getId().equals(id));
    }
}
