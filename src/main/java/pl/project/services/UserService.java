package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.User;
import pl.project.repositories.UserRepository;
import pl.project.repositoriesCRUD.UserCRUDRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserCRUDRepository userCRUDRepository;
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        userCRUDRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUser(Integer id) {
        User user = userCRUDRepository.findById(id).get();
        return user;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        User user = userCRUDRepository.findByEmailAndPassword(email, password);
        return user;
    }

    public void addUser(User user) {
        userCRUDRepository.save(user);
    }


    public void updateUser(Integer id, User user) {
        userCRUDRepository.save(user);
    }

    public void settleUserMoney(Integer userId, Float money) {
        userRepository.settleMoneyUser(userId, money);
    }

    public void deleteUser(Integer id) {
        userCRUDRepository.deleteById(id);
    }

}
