package manage.task.taskmanagement.service;

import manage.task.taskmanagement.model.User;
import manage.task.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUserName(String email) {
        return userRepository.getUserByUserName(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
