package com.example.springmvc.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "reply")
@ToString(exclude = "board")
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "rno")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rno;

    private String replyText;

    private String replyer;

    @CreationTimestamp
    private Timestamp regDt;

    @UpdateTimestamp
    private Timestamp updatedDt;

    @JsonIgnore
    @ManyToOne
    private Board board;
}
