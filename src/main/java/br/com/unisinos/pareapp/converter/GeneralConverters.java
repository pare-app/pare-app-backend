package br.com.unisinos.pareapp.converter;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.facade.impl.ExerciseQuestionFacade;
import br.com.unisinos.pareapp.model.dto.answer.AnswerCreationDto;
import br.com.unisinos.pareapp.model.dto.answer.AnswerEntityDto;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomCreationDto;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.dto.exercise.ExerciseCreationDto;
import br.com.unisinos.pareapp.model.dto.exercise.ExerciseEntityDto;
import br.com.unisinos.pareapp.model.dto.exercisequestion.ExerciseQuestionCreationDto;
import br.com.unisinos.pareapp.model.dto.exercisequestion.ExerciseQuestionEntityDto;
import br.com.unisinos.pareapp.model.dto.pair.PairCreationDto;
import br.com.unisinos.pareapp.model.dto.pair.PairEntityDto;
import br.com.unisinos.pareapp.model.dto.question.QuestionCreationDto;
import br.com.unisinos.pareapp.model.dto.question.QuestionEntityDto;
import br.com.unisinos.pareapp.model.dto.session.SessionCreationDto;
import br.com.unisinos.pareapp.model.dto.session.SessionEntityDto;
import br.com.unisinos.pareapp.model.dto.solution.SolutionCreationDto;
import br.com.unisinos.pareapp.model.dto.solution.SolutionEntityDto;
import br.com.unisinos.pareapp.model.dto.user.LoginDto;
import br.com.unisinos.pareapp.model.dto.user.RegisterDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import com.github.roookeee.datus.api.Datus;
import com.github.roookeee.datus.api.Mapper;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class GeneralConverters {
    private final EntityFacade<UserEntityDto> userFacade;
    private final EntityFacade<PairEntityDto> pairFacade;
    private final EntityFacade<ClassroomEntityDto> classroomFacade;
    private final EntityFacade<ExerciseEntityDto> exerciseFacade;
    private final EntityFacade<QuestionEntityDto> questionFacade;
    private final EntityFacade<SessionEntityDto> sessionFacade;
    private final EntityFacade<ExerciseQuestionEntityDto> exerciseQuestionFacade;

    @Bean
    public Mapper<LoginDto, UserEntityDto> userLoginConverter() {
        return Datus.forTypes(LoginDto.class, UserEntityDto.class).mutable(UserEntityDto::new)
                .from(LoginDto::getUsername)
                    .into(UserEntityDto::setUsername)
                .from(LoginDto::getPassword)
                    .into(UserEntityDto::setPassword)
                .build();
    }

    @Bean
    public Mapper<RegisterDto, UserEntityDto> userRegisterConverter() {
        return Datus.forTypes(RegisterDto.class, UserEntityDto.class).mutable(UserEntityDto::new)
                .from(RegisterDto::getName)
                    .into(UserEntityDto::setName)
                .from(RegisterDto::getUsername)
                    .into(UserEntityDto::setUsername)
                .from(RegisterDto::getPassword)
                    .into(UserEntityDto::setPassword)
                .build();
    }

    @Bean
    public Mapper<ClassroomCreationDto, ClassroomEntityDto> classroomCreationConverter() {
        return Datus.forTypes(ClassroomCreationDto.class, ClassroomEntityDto.class).mutable(ClassroomEntityDto::new)
                .from(ClassroomCreationDto::getName)
                    .into(ClassroomEntityDto::setName)
                .from(ClassroomCreationDto::getOwner)
                    .map(owner -> userFacade.find(owner.getId()))
                    .into(ClassroomEntityDto::setOwner)
                .build();
    }

    @Bean
    public Mapper<PairCreationDto, PairEntityDto> pairCreationConverter() {
        return Datus.forTypes(PairCreationDto.class, PairEntityDto.class).mutable(PairEntityDto::new)
                .from(PairCreationDto::getClassroom)
                    .map(classroom -> classroomFacade.find(classroom.getId()))
                    .into(PairEntityDto::setClassroom)
                .from(PairCreationDto::getStudent1)
                    .map(student1 -> userFacade.find(student1.getId()))
                    .into(PairEntityDto::setStudent1)
                .from(PairCreationDto::getStudent2)
                    .map(student2 -> userFacade.find(student2.getId()))
                    .into(PairEntityDto::setStudent2)
                .build();
    }

    @Bean
    public Mapper<ExerciseCreationDto, ExerciseEntityDto> exerciseCreationConverter() {
        return Datus.forTypes(ExerciseCreationDto.class, ExerciseEntityDto.class).mutable(ExerciseEntityDto::new)
                .from(ExerciseCreationDto::getDescription)
                    .into(ExerciseEntityDto::setDescription)
                .from(ExerciseCreationDto::getClassroom)
                    .nullsafe()
                    .given(Objects::nonNull, classroom -> Sets.newHashSet(classroomFacade.find(classroom.getId())))
                    .orElse(Sets.newHashSet())
                    .into(ExerciseEntityDto::setClassrooms)
                .build();
    }

    @Bean
    public Mapper<QuestionCreationDto, QuestionEntityDto> questionCreationConverter() {
        return Datus.forTypes(QuestionCreationDto.class, QuestionEntityDto.class).mutable(QuestionEntityDto::new)
                .from(QuestionCreationDto::getDescription)
                    .into(QuestionEntityDto::setDescription)
                .from(QuestionCreationDto::getImage)
                    .into(QuestionEntityDto::setImage)
                .build();
    }

    @Bean
    public Mapper<ExerciseQuestionCreationDto, ExerciseQuestionEntityDto> exerciseQuestionCreationConverter() {
        return Datus.forTypes(ExerciseQuestionCreationDto.class, ExerciseQuestionEntityDto.class).mutable(ExerciseQuestionEntityDto::new)
                .from(ExerciseQuestionCreationDto::getExercise)
                    .map(exercise -> exerciseFacade.find(exercise.getId()))
                    .into(ExerciseQuestionEntityDto::setExercise)
                .from(ExerciseQuestionCreationDto::getQuestion)
                    .map(question -> questionFacade.find(question.getId()))
                    .into(ExerciseQuestionEntityDto::setQuestion)
                .from(ExerciseQuestionCreationDto::getOrder)
                .into(ExerciseQuestionEntityDto::setOrder)
                .build();
    }

    @Bean
    public Mapper<SolutionCreationDto, SolutionEntityDto> solutionCreationConverter() {
        return Datus.forTypes(SolutionCreationDto.class, SolutionEntityDto.class).mutable(SolutionEntityDto::new)
                .from(SolutionCreationDto::getQuestion)
                    .map(question -> questionFacade.find(question.getId()))
                    .into(SolutionEntityDto::setQuestion)
                .from(SolutionCreationDto::getImage)
                    .into(SolutionEntityDto::setImage)
                .build();
    }

    @Bean
    public Mapper<AnswerCreationDto, AnswerEntityDto> answerCreationConverter() {
        return Datus.forTypes(AnswerCreationDto.class, AnswerEntityDto.class).mutable(AnswerEntityDto::new)
                .from(AnswerCreationDto::getQuestion)
                    .map(question -> questionFacade.find(question.getId()))
                    .into(AnswerEntityDto::setQuestion)
                .from(AnswerCreationDto::getSession)
                    .map(session -> sessionFacade.find(session.getId()))
                    .into(AnswerEntityDto::setSession)
                .build();
    }

    @Bean
    public Mapper<SessionCreationDto, SessionEntityDto> sessionCreationConverter() {
        return Datus.forTypes(SessionCreationDto.class, SessionEntityDto.class).mutable(SessionEntityDto::new)
                .from(SessionCreationDto::getExercise)
                    .map(exercise -> exerciseFacade.find(exercise.getId()))
                    .into(SessionEntityDto::setExercise)
                .from(SessionCreationDto::getPair)
                    .map(pair -> pairFacade.find(pair.getId()))
                    .into(SessionEntityDto::setPair)
                .build();
    }
}
