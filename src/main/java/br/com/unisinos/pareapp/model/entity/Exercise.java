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
    @ManyToMany(mappedBy = "exercises",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Classroom> classrooms;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Question> questions;

    @OneToMany(mappedBy = "exercise",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Session> sessions;
}
