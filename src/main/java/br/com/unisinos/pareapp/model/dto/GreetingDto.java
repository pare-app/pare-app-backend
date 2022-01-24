package br.com.unisinos.pareapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GreetingDto extends BaseDto {
	private String content;

	public GreetingDto(Integer id, String content) {
		super(id);
		this.content = content;
	}
}
