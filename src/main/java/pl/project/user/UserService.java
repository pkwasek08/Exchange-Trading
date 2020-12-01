package pl.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.execDetails.ExecDetails;
import pl.project.execDetails.ExecDetailsHelper;

import java.time.OffsetDateTime;
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

    public ExecDetails addUser(User user) {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();
        execHelper.setStartDbTime(OffsetDateTime.now());
        User newUser =  userRepository.save(user);
        execHelper.addNewDbTime();
        return new ExecDetails(execHelper.getDbTime(), execHelper.getExecTime());
    }

    public ExecDetails addUserList(List<User> userList) {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();
        execHelper.setStartDbTime(OffsetDateTime.now());
        userList.stream().forEach(user -> userRepository.save(user));
        execHelper.addNewDbTime();
        return new ExecDetails(execHelper.getDbTime(), execHelper.getExecTime());
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
