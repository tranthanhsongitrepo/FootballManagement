package com.example.backend.player;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tbl_player")
@Getter
@Setter
public class Player {
    @Id
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
    private String nationality;
    private Date dob;

//    @Override
//    public String toString() {
//        return "{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", nationality='" + nationality + '\'' +
//                ", dob=" + dob +
//                '}';
//    }
}
