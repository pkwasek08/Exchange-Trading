package pl.project.user;

import lombok.Data;

import java.util.List;

@Data
public class ExecDetailsUser {
    private Integer exeTime;
    private Integer dbTime;
    private User user;
    private List<User> userList;

    public ExecDetailsUser(Integer exeTime, Integer dbTime, User user) {
        this.exeTime = exeTime;
        this.dbTime = dbTime;
        this.user = user;
    }

    public ExecDetailsUser(Integer exeTime, Integer dbTime, List<User> userList) {
        this.exeTime = exeTime;
        this.dbTime = dbTime;
        this.userList = userList;
    }

}
