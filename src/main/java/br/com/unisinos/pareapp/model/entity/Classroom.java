package br.com.unisinos.pareapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Classroom extends BaseEntity {
    @NotEmpty(message = "empty name")
    private String name;
    @NotNull(message = "empty owner")
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<User> students;
    @OneToMany(mappedBy = "classroom",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pair> pairs;
}
