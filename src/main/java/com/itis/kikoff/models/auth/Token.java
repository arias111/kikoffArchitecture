package com.itis.kikoff.models.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date time_of_creating;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    public Token(String token, Date time, User user) {
        this.token = token;
        this.time_of_creating = time;
        this.user = user;
    }
}
