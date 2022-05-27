package com.example.healthiu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "message")
@Data
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String senderLogin;

    @Column
    private String recipientLogin;

    @Column
    private String content;

    @Column
    private Timestamp timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public void setSenderLogin(String senderId) {
        this.senderLogin = senderId;
    }

    public String getRecipientLogin() {
        return recipientLogin;
    }

    public void setRecipientLogin(String recipientId) {
        this.recipientLogin = recipientId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Message(String senderLogin, String recipientId, String content) {
        this.senderLogin = senderLogin;
        this.recipientLogin = recipientId;
        this.content = content;
        this.timestamp = new Timestamp(new Date().getTime());
    }

    @Override
    public String toString() {
        return "Message [from=" + senderLogin + ", to=" + recipientLogin + ", message=" + content + "]";
    }


}
