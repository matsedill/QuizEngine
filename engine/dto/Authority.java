package engine.dto;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORITIES")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name ="USERNAME")
    private String username;
    @Column(name = "AUTHORITY")
    private String authority;
    public Authority() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return username;
    }
    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}