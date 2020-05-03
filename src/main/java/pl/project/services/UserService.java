package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entieties.User;
import pl.project.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUser(Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }


    public void updateUser(Integer id, User user) {
        userRepository.save(user);
    }


    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
