package com.example.Whatsup.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "getUserMessages",
                procedureName = "getUserMessages",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type = Integer.class)
                },
                resultClasses = {Message.class}
        ),
        @NamedStoredProcedureQuery(
                name = "insertUserMessage",
                procedureName = "insertUserMessage",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "message", type = String.class)
                },
                resultClasses = {Message.class}
        )
})



@Entity(name = "Message")
@Data
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
    @JoinColumn(name = "user_id")
    private Usuario userId;
    @Column(name = "content")
    private String content;
    @Column(name = "message_date")
    private Date messageDate;
}
