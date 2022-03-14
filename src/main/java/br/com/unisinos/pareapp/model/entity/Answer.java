package br.com.unisinos.pareapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {
                        "session_id",
                        "question_id"}
        )})
public class Answer extends BaseEntity {
    @NotNull(message = "empty session")
    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @NotNull(message = "empty question")
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private byte[] image;
}
