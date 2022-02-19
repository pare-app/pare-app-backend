package br.com.unisinos.pareapp.model.dto.pair;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PairCreationDto {
    private Integer classroomId;
    private Integer student1Id;
    private Integer student2Id;
}
