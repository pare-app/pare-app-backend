package br.com.unisinos.pareapp.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
            "classroom_id",
            "student1_id",
            "student2_id"}
    )})
public class Pair extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    @NotNull(message = "empty classroom")
    private Classroom classroom;
    @ManyToOne
    @JoinColumn(name = "student1_id")
    @NotNull(message = "empty student")
    private User student1;
    @ManyToOne
    @JoinColumn(name = "student2_id")
    @NotNull(message = "empty student")
    private User student2;
}
