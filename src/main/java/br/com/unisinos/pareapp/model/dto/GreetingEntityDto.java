package br.com.unisinos.pareapp.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GreetingEntityDto extends BaseEntityDto {
	private String content;

	public GreetingEntityDto(Integer id, String content) {
		super(id);
		this.content = content;
	}
}
