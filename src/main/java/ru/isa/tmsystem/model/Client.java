package ru.isa.tmsystem.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Builder
@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String name;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    ACCESS_TYPE access_type;


//    @OneToMany(mappedBy = "author",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            fetch = FetchType.LAZY)
//    @Builder.Default
//    private List<Task> authoredTasks = new ArrayList<>();


    public enum ACCESS_TYPE {
        MASTER,
        SLAVE;
    }

    public Client(String name, String email, String password, ACCESS_TYPE access_type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.access_type = access_type;
    }


    public Client() {

    }

//    public List<Task> getAuthoredTasks() {
//        return authoredTasks;
//    }
//
//    public void setAuthoredTasks(List<Task> authoredTasks) {
//        this.authoredTasks = authoredTasks;
//    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ACCESS_TYPE getAccess_type() {
        return access_type;
    }

    public void setAccess_type(ACCESS_TYPE access_type) {
        this.access_type = access_type;
    }
}


