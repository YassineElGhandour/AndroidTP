package com.example.yassine.test;


public class Login {
    private int id;
    private String login;
    private String pass;

    public Login() {
    }

    public Login(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public int getId(){
        return this.id;
    }
    public String getLogin(){
        return this.login;
    }
    public String getPass(){
        return this.pass;
    }
    public void setId(int i){
        this.id=i;
    }
    public void setLogin(String login){
        this.login=login;
    }
    public void setPass(String pass){
        this.pass=pass;
    }
}
