package com.example.Whatsup.entities;

import jakarta.persistence.*;
import lombok.Data;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "createNewChat",
                procedureName = "createNewChat",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userOne", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userTwo", type = Integer.class)
                },
                resultClasses = {Chat.class}
        )
})

@Entity(name = "Chat")
@Data
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Integer chatId;
    @ManyToOne()
    @JoinColumn(name = "userone_id", referencedColumnName = "user_id")
    private Usuario userOne;
    @ManyToOne()
    @JoinColumn(name = "usertwo_id", referencedColumnName = "user_id")
    private Usuario userTwo;
}
