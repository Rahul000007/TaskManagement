package manage.task.taskmanagement.controller;

import manage.task.taskmanagement.data.ApiResponse;
import manage.task.taskmanagement.model.Task;
import manage.task.taskmanagement.model.User;
import manage.task.taskmanagement.service.TaskService;
import manage.task.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping("/add-task")
    public ResponseEntity<?> createTask(@RequestBody Task task, Principal principal) {
        User user = userService.getUserByUserName(principal.getName());
        task.setUser(user);
        taskService.addTask(task);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "New Task Added"), HttpStatus.OK);
    }

    @GetMapping("/get-task")
    public ResponseEntity<?> getTask(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "6") int size,Principal principal) {
        PageRequest pageable = PageRequest.of(page, size);
        User user = userService.getUserByUserName(principal.getName());
        Page<Task> tasks = taskService.getTask(user.getId(), pageable);
        return new ResponseEntity<ApiResponse>(new ApiResponse(tasks, true, "Task Page"), HttpStatus.OK);
    }

    @GetMapping("/get-taskById/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable String taskId) {
        Task task = taskService.getTaskById(Integer.parseInt(taskId));
        return new ResponseEntity<>(new ApiResponse(task), HttpStatus.OK);
    }

    @DeleteMapping("/delete-task/{taskId}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable String taskId) {
        try {
            taskService.deleteTaskById(Integer.parseInt(taskId));
            return new ResponseEntity<>(new ApiResponse(true, "Task deleted successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Error deleting task. Please try again."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-task")
    public ResponseEntity<?> updateTask(@RequestBody Task task) {
        Task updatedTask = taskService.getTaskById(task.getId());
        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());
        updatedTask.setPriority(task.getPriority());
        updatedTask.setStatus(task.getStatus());
        taskService.addTask(updatedTask);
        return new ResponseEntity<>(new ApiResponse(true, "Task Updated successfully"), HttpStatus.OK);
    }

    @GetMapping("/search-task")
    public ResponseEntity<?> searchTask(@RequestParam String keyword,Principal principal) {
        User user = userService.getUserByUserName(principal.getName());
        List<Task> tasks = taskService.searchTask(keyword, user);
        return new ResponseEntity<>(new ApiResponse(tasks, true, "Search Results"), HttpStatus.OK);
    }

    @GetMapping("/filter-tasks")
    public ResponseEntity<?> filterTasks(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "6") int size,
                                         @RequestParam(required = false) String status,
                                         @RequestParam(required = false) String priorityFilter,Principal principal) {
        PageRequest pageable = PageRequest.of(page, size);
        User user = userService.getUserByUserName(principal.getName());
        Page<Task> filteredTasks = taskService.filterTasks(user, status, priorityFilter, pageable);
        return new ResponseEntity<>(new ApiResponse(filteredTasks, true, "Filtered Tasks"), HttpStatus.OK);
    }


}
