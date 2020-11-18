package pl.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDAO userDAO;

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUser(Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        return user;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }


    public void updateUser(Integer id, User user) {
        userRepository.save(user);
    }

    public void settleUserMoney(Integer userId, Float money) {
        userDAO.settleMoneyUser(userId, money);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
