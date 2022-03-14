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
        @UniqueConstraint(columnNames = {"question_id"})
})
public class Solution extends BaseEntity {
    @NotNull(message = "empty question")
    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @NotNull(message = "empty image")
    private byte[] image;
}
