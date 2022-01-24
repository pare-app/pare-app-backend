package br.com.unisinos.pareapp.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Classroom extends BaseEntity {
    @NotEmpty(message = "empty name")
    private String name;
    @NotNull(message = "empty owner")
    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToMany(fetch = FetchType.EAGER)
    private List<User> students;
}
