package engine.dto;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Column(name = "USERNAME")
    private String userName;
    private String email;
    private String password;
    @Column(name = "ENABLED")
    private boolean enabled = true;
    @Column(name = "AUTHORITIES")
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Authority> roles;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String username) {
        this.userName = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Authority> getRoles() {
        return roles;
    }
    public void setRoles(Set<Authority> roles) {
        this.roles = roles;
    }
}
