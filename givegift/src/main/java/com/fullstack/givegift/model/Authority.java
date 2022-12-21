package com.fullstack.givegift.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="Authority")
public class Authority implements GrantedAuthority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Getter @Setter
    private Long id;
    private @Getter @Setter @ManyToOne Gigi gigi;
    private @Setter String authority;

    public Authority() {}


    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
