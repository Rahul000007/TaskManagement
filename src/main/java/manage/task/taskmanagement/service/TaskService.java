package manage.task.taskmanagement.service;

import manage.task.taskmanagement.model.Task;
import manage.task.taskmanagement.model.User;
import manage.task.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public Page<Task> getTask(int userId, Pageable pageable) {
        return taskRepository.findTaskByUser(userId, pageable);
    }

    public void deleteTaskById(int taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task getTaskById(int taskId) {
        return taskRepository.findById(taskId).get();
    }

    public List<Task> searchTask(String keyword, User user) {
        return taskRepository.findByTitleContainingAndUser(keyword, user);
    }


    public Page<Task> filterTasks(User user, String status, String priorityFilter, Pageable pageable) {

        if ("all".equalsIgnoreCase(status) && "all".equalsIgnoreCase(priorityFilter)) {
            // Return all tasks for a particular user
            return taskRepository.findTasksByUser(user, pageable);
        } else if (!"all".equalsIgnoreCase(status) && "all".equalsIgnoreCase(priorityFilter)) {
            // Return tasks filtered by status for a particular user
            return taskRepository.findTasksByUserAndStatus(user, status, pageable);
        } else if ("all".equalsIgnoreCase(status) && !"all".equalsIgnoreCase(priorityFilter)) {
            // Return tasks filtered by priority for a particular user
            return taskRepository.findTasksByUserAndPriority(user, priorityFilter, pageable);
        } else {
            // Return tasks filtered by both status and priority for a particular user
            return taskRepository.findTasksByUserAndStatusAndPriority(user, status, priorityFilter, pageable);
        }
    }
}
