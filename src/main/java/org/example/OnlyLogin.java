package org.example;

public class OnlyLogin {//создала класс чтобы понять почему тест с логином курьера без пароля падает
    private String login;

    public OnlyLogin (String login){
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
