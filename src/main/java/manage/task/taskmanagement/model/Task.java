package manage.task.taskmanagement.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    private String status;
    private String priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
