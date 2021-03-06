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
@Table(uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {
                        "exercise_id",
                        "pair_id"}
        )})
public class Session extends BaseEntity{
    @NotNull(message = "empty exercise")
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @NotNull(message = "empty pair")
    @ManyToOne
    @JoinColumn(name = "pair_id")
    private Pair pair;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Answer> answers;
}
