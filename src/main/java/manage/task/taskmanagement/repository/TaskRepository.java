package manage.task.taskmanagement.repository;


import manage.task.taskmanagement.model.Task;
import manage.task.taskmanagement.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("from Task as t where t.user.id=:userId")
    Page<Task> findTaskByUser(@Param("userId") int userId, Pageable pageable);

    List<Task> findByTitleContainingAndUser(String keyword, User user);

    Page<Task> findTasksByUser(User user, Pageable pageable);

    Page<Task> findTasksByUserAndStatus(User user, String status, Pageable pageable);

    Page<Task> findTasksByUserAndPriority(User user, String priority, Pageable pageable);

    Page<Task> findTasksByUserAndStatusAndPriority(User user, String status, String priority, Pageable pageable);
}
