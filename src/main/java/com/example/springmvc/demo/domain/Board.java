package com.example.springmvc.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Table(name = "board")
@Entity
@ToString(exclude = "replies")
@EqualsAndHashCode(of = "bno")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bno;

    private String title;

    private String writer;

    private String content;

    @CreationTimestamp
    private Timestamp regDt;

    @UpdateTimestamp
    private Timestamp upDt;

    @OneToMany(mappedBy = "board")
    private Set<Reply> replies;
}
