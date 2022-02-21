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
    @ManyToMany(mappedBy = "questions",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Exercise> exercises;

    @NotNull(message = "empty image")
    private byte[] image;

    @OneToOne
    @JoinColumn(name = "solution_id")
    private Solution solution;
}
