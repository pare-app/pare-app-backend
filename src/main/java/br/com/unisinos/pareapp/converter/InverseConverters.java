package br.com.unisinos.pareapp.converter;

import br.com.unisinos.pareapp.model.dto.answer.AnswerEntityDto;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.dto.exercise.ExerciseEntityDto;
import br.com.unisinos.pareapp.model.dto.exercisequestion.ExerciseQuestionEntityDto;
import br.com.unisinos.pareapp.model.dto.pair.PairEntityDto;
import br.com.unisinos.pareapp.model.dto.question.QuestionEntityDto;
import br.com.unisinos.pareapp.model.dto.session.SessionEntityDto;
import br.com.unisinos.pareapp.model.dto.solution.SolutionEntityDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.model.entity.*;
import br.com.unisinos.pareapp.service.EntityService;
import com.github.roookeee.datus.api.Datus;
import com.github.roookeee.datus.api.Mapper;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class InverseConverters {
    private final PasswordEncoder passwordEncoder;
    private final EntityService<User> userService;
    private final EntityService<Classroom> classroomService;
    private final EntityService<Pair> pairService;
    private final EntityService<Exercise> exerciseService;
    private final EntityService<ExerciseQuestion> exerciseQuestionService;
    private final EntityService<Session> sessionService;
    private final EntityService<Question> questionService;
    private final EntityService<Solution> solutionService;
    private final EntityService<Answer> answerService;

    @Bean
    public Mapper<SolutionEntityDto, Solution> solutionInverseConverter() {
        return Datus.forTypes(SolutionEntityDto.class,Solution.class).mutable(Solution::new)
                .from(SolutionEntityDto::getId)
                    .into(Solution::setId)
                .from(SolutionEntityDto::getImage)
                    .into(Solution::setImage)
                .from(SolutionEntityDto::getQuestion)
                    .map(question ->
                        questionService.find(question.getId())
                                .orElseThrow(EntityNotFoundException::new))
                    .into(Solution::setQuestion)
                .build();
    }

    @Bean
    public Mapper<AnswerEntityDto, Answer> answerInverseConverter() {
        return Datus.forTypes(AnswerEntityDto.class,Answer.class).mutable(Answer::new)
                .from(AnswerEntityDto::getId)
                    .into(Answer::setId)
                .from(AnswerEntityDto::getImage)
                    .into(Answer::setImage)
                .from(AnswerEntityDto::getQuestion)
                    .map(question ->
                            questionService.find(question.getId())
                                    .orElseThrow(EntityNotFoundException::new))
                    .into(Answer::setQuestion)
                .from(AnswerEntityDto::getSession)
                    .map(session ->
                            sessionService.find(session.getId())
                                    .orElseThrow(EntityNotFoundException::new))
                    .into(Answer::setSession)
                .build();
    }

    @Bean
    public Mapper<QuestionEntityDto, Question> questionInverseConverter() {
        return Datus.forTypes(QuestionEntityDto.class, Question.class).mutable(Question::new)
                .from(QuestionEntityDto::getId)
                    .into(Question::setId)
                .from(QuestionEntityDto::getImage)
                    .into(Question::setImage)
                .from(QuestionEntityDto::getDescription)
                    .into(Question::setDescription)
                .from(QuestionEntityDto::getSolution)
                    .nullsafe()
                    .map(solution ->
                        solutionService.find(solution.getId())
                                .orElseThrow(EntityNotFoundException::new))
                    .into(Question::setSolution)
                .from(QuestionEntityDto::getExercises)
                    .given(Objects::nonNull, exercises -> exercises.stream()
                            .map(exercise -> exerciseQuestionService.find(exercise.getId())
                                    .orElseThrow(EntityNotFoundException::new))
                            .collect(Collectors.toSet()))
                    .orElse(Sets.newHashSet())
                    .into(Question::setExercises)
                .build();
    }

    @Bean
    public Mapper<ExerciseQuestionEntityDto, ExerciseQuestion> exerciseQuestionInverseConverter() {
        return Datus.forTypes(ExerciseQuestionEntityDto.class, ExerciseQuestion.class).mutable(ExerciseQuestion::new)
                .from(ExerciseQuestionEntityDto::getId)
                    .into(ExerciseQuestion::setId)
                .from(ExerciseQuestionEntityDto::getOrder)
                    .into(ExerciseQuestion::setOrder)
                .from(ExerciseQuestionEntityDto::getExercise)
                    .map(exercise ->
                            exerciseService.find(exercise.getId())
                                    .orElseThrow(EntityNotFoundException::new))
                    .into(ExerciseQuestion::setExercise)
                .from(ExerciseQuestionEntityDto::getQuestion)
                    .map(question ->
                            questionService.find(question.getId())
                                    .orElseThrow(EntityNotFoundException::new))
                    .into(ExerciseQuestion::setQuestion)
                .build();
    }

    @Bean
    public Mapper<UserEntityDto, User> userInverseConverter() {
        return Datus.forTypes(UserEntityDto.class, User.class).mutable(User::new)
                .from(UserEntityDto::getId)
                    .into(User::setId)
                .from(UserEntityDto::getName)
                    .into(User::setName)
                .from(UserEntityDto::getUsername)
                    .into(User::setUsername)
                .from(UserEntityDto::getPassword)
                    .map(passwordEncoder::encode)
                    .into(User::setPassword)
                .from(UserEntityDto::getClassrooms)
                    .given(Objects::nonNull, classrooms ->
                            classrooms.stream()
                                    .map(classroom -> classroomService.find(classroom.getId())
                                            .orElseThrow(EntityNotFoundException::new))
                                    .collect(Collectors.toSet()))
                    .orElse(Sets.newHashSet())
                .into(User::setClassrooms)
                .build();
    }

    @Bean
    public Mapper<ClassroomEntityDto, Classroom> classroomInverseConverter() {
        return Datus.forTypes(ClassroomEntityDto.class, Classroom.class).mutable(Classroom::new)
                .from(ClassroomEntityDto::getId)
                    .into(Classroom::setId)
                .from(ClassroomEntityDto::getName)
                    .into(Classroom::setName)
                .from(ClassroomEntityDto::getOwner)
                    .map(owner -> userService.find(owner.getId())
                            .orElseThrow(EntityNotFoundException::new))
                    .into(Classroom::setOwner)
                .from(ClassroomEntityDto::getStudents)
                    .given(Objects::nonNull, students -> students.stream()
                            .map(student -> userService.find(student.getId())
                                    .orElseThrow(EntityNotFoundException::new))
                            .collect(Collectors.toSet()))
                    .orElse(Sets.newHashSet())
                    .into(Classroom::setStudents)
                .from(ClassroomEntityDto::getExercises)
                    .given(Objects::nonNull, exercises -> exercises.stream()
                            .map(exercise -> exerciseService.find(exercise.getId())
                                    .orElseThrow(EntityNotFoundException::new))
                            .collect(Collectors.toSet()))
                    .orElse(Sets.newHashSet())
                    .into(Classroom::setExercises)
                .from(ClassroomEntityDto::getPairs)
                    .given(Objects::nonNull, pairs -> pairs.stream()
                            .map(pair -> pairService.find(pair.getId())
                                    .orElseThrow(EntityNotFoundException::new))
                            .collect(Collectors.toSet()))
                    .orElse(Sets.newHashSet())
                    .into(Classroom::setPairs)
                .build();
    }

    @Bean
    public Mapper<PairEntityDto, Pair> pairInverseConverter() {
        return Datus.forTypes(PairEntityDto.class, Pair.class).mutable(Pair::new)
                .from(PairEntityDto::getId)
                    .into(Pair::setId)
                .from(PairEntityDto::getClassroom)
                    .map(classroom -> classroomService.find(classroom.getId())
                            .orElseThrow(EntityNotFoundException::new))
                    .into(Pair::setClassroom)
                .from(PairEntityDto::getStudent1)
                    .map(student1 -> userService.find(student1.getId())
                            .orElseThrow(EntityNotFoundException::new))
                    .into(Pair::setStudent1)
                .from(PairEntityDto::getStudent2)
                    .map(student2 -> userService.find(student2.getId())
                            .orElseThrow(EntityNotFoundException::new))
                    .into(Pair::setStudent2)
                .build();
    }

    @Bean
    public Mapper<ExerciseEntityDto, Exercise> exerciseInverseConverter() {
        return Datus.forTypes(ExerciseEntityDto.class, Exercise.class).mutable(Exercise::new)
                .from(ExerciseEntityDto::getId)
                    .into(Exercise::setId)
                .from(ExerciseEntityDto::getDescription)
                    .into(Exercise::setDescription)
                .from(ExerciseEntityDto::getClassrooms)
                    .given(Objects::nonNull, classrooms -> classrooms.stream()
                            .map(classroom -> classroomService.find(classroom.getId())
                                    .orElseThrow(EntityNotFoundException::new))
                            .collect(Collectors.toSet()))
                    .orElse(Sets.newHashSet())
                    .into(Exercise::setClassrooms)
                .from(ExerciseEntityDto::getQuestions)
                    .given(Objects::nonNull, questions -> questions.stream()
                            .map(question -> exerciseQuestionService.find(question.getId())
                                    .orElseThrow(EntityNotFoundException::new))
                            .collect(Collectors.toSet()))
                    .orElse(Sets.newHashSet())
                    .into(Exercise::setQuestions)
                .from(ExerciseEntityDto::getSessions)
                    .given(Objects::nonNull, sessions -> sessions.stream()
                            .map(session -> sessionService.find(session.getId())
                                    .orElseThrow(EntityNotFoundException::new))
                            .collect(Collectors.toSet()))
                    .orElse(Sets.newHashSet())
                    .into(Exercise::setSessions)
                .build();
    }

    @Bean
    public Mapper<SessionEntityDto, Session> sessionInverseConverter() {
        return Datus.forTypes(SessionEntityDto.class, Session.class).mutable(Session::new)
                .from(SessionEntityDto::getId)
                    .into(Session::setId)
                .from(SessionEntityDto::getExercise)
                    .map(exercise -> exerciseService.find(exercise.getId())
                            .orElseThrow(EntityNotFoundException::new))
                    .into(Session::setExercise)
                .from(SessionEntityDto::getPair)
                    .map(pair -> pairService.find(pair.getId())
                            .orElseThrow(EntityNotFoundException::new))
                    .into(Session::setPair)
                .from(SessionEntityDto::getAnswers)
                    .given(Objects::nonNull, answers -> answers.stream()
                            .map(answer -> answerService.find(answer.getId())
                                    .orElseThrow(EntityNotFoundException::new))
                            .collect(Collectors.toSet()))
                    .orElse(Sets.newHashSet())
                    .into(Session::setAnswers)
                .build();
    }

}
