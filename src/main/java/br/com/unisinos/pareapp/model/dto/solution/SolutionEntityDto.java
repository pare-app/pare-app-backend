package br.com.unisinos.pareapp.model.dto.solution;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.dto.question.QuestionEntityDto;

public class SolutionEntityDto extends BaseEntityDto {
    private QuestionEntityDto question;
    private byte[] image;
}
