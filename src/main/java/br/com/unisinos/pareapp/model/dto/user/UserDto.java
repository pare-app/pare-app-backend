package br.com.unisinos.pareapp.model.dto.user;

import br.com.unisinos.pareapp.model.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto extends BaseDto {
    private String name;
    private String username;
    @JsonIgnore
    private String password;
}
