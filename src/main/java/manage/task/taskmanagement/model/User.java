package manage.task.taskmanagement.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
}
