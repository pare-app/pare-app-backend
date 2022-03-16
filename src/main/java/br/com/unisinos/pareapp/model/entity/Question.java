package br.com.unisinos.pareapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question extends BaseEntity {
    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<ExerciseQuestion> exercises;

    @NotNull(message = "empty image")
    private byte[] image;

    @OneToOne(mappedBy="question")
    private Solution solution;

    private String description;
}
