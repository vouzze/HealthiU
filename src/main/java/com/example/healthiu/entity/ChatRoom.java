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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userLogin;

    @Column
    private String doctorLogin;

    public ChatRoom(String userLogin, String doctorLogin) {
        this.userLogin = userLogin;
        this.doctorLogin = doctorLogin;
    }
}
