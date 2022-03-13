package br.com.unisinos.pareapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
