package server;

import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {

    private class UserData {
        String login;
        String password;
        String nickname;

        public UserData(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }
    }

    private List<UserData> users;

    public SimpleAuthService() {
        users = new ArrayList<>();
        users.add(new UserData("admin","admin","admin"));
        users.add(new UserData("admin2","admin2","admin2"));
        users.add(new UserData("admin3","admin3","admin3"));
        for(int i = 1;i<10; i++) {
            users.add(new UserData("login" + i,"pass" + i,"nick" + i));
        }
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        for (UserData user : users) {
            if(user.login.equals(login) && user.password.equals(password)) {
                return user.nickname;
            }
        }
        return null;
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        for(UserData user : users) {
            if(user.login.equals(login) || user.password.equals(password) || user.nickname.equals(nickname)){
                return false;
            }
        }
        users.add(new UserData(login,password,nickname));
        return true;
    }
}
