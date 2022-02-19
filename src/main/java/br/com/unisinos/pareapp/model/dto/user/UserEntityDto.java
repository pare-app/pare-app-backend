package br.com.unisinos.pareapp.model.dto.user;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class UserEntityDto extends BaseEntityDto {
    private String name;
    private String username;
    @JsonIgnore
    private String password;
}
