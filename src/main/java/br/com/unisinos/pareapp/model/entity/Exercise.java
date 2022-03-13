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
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Classroom> classrooms;

    @ManyToMany(mappedBy = "exercises", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Question> questions;

    @OneToMany(mappedBy = "exercise",fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Session> sessions;

    private String description;
}
