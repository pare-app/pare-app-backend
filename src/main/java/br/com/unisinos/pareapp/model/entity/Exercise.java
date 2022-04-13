package br.com.unisinos.pareapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Exercise extends BaseEntity{
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Classroom> classrooms;

    @OrderBy("order ASC")
    @OneToMany(mappedBy = "exercise", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ExerciseQuestion> questions;

    @OneToMany(mappedBy = "exercise",fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Session> sessions;

    private String description;
}
