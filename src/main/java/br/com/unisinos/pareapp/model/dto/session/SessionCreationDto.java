package br.com.unisinos.pareapp.model.dto.session;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class SessionCreationDto {
    private BaseEntityDto exercise;
    private BaseEntityDto pair;
}
