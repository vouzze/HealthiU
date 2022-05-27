package com.example.healthiu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "chat_room")
@Data
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String userLogin;

    @Column
    private String doctorLogin;
}
