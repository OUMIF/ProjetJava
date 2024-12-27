package model;

public class User {

    private Integer id;
    private String Email;
    private String Password;
    private Integer Role;



    public User() {}
    public User( Integer id ,String Email) {
        this.id = id;
        this.Email = Email;
    }
    public User(Integer id, String email, String password, Integer role) {
        this.id = id;
        Email = email;
        Password = password;
        Role = role;
    }

    public Integer getRole() {
        return Role;
    }

    public void setRole(Integer role) {
        Role = role;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
