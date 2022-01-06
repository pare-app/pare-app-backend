package br.com.unisinos.pareapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Greeting {

	private long id;
	private String content;

	@Override
	public String toString() {
		return "Greeting{" +
				"id='" + id + '\'' +
				", content=" + content +
				'}';
	}
}
