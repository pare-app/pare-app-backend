package br.com.unisinos.pareapp.model.dto.pair;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PairCreationDto {
    private BaseEntityDto classroom;
    private BaseEntityDto student1;
    private BaseEntityDto student2;
}
