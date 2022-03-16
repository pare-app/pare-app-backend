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
                        "exercise_id",
                        "question_id"}),
        @UniqueConstraint(
                columnNames = {
                        "exercise_id",
                        "order_value"})
        })
public class ExerciseQuestion extends BaseEntity{
    @NotNull(message = "empty exercise")
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @NotNull(message = "empty question")
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "order_value")
    private Integer order;
}
