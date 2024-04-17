package com.example.animetracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //model want to persist in db
@Table(name = "roles") //optional anno, names the table however we want
public class Role implements GrantedAuthority {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id") //optional, usually used to add restrictions
    private Integer roleId;

    private String authority;

    public Role(String role) {
        this.authority = role;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}

