package br.com.unisinos.pareapp.converter;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.dto.answer.AnswerEntityDto;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.dto.exercise.ExerciseEntityDto;
import br.com.unisinos.pareapp.model.dto.pair.PairEntityDto;
import br.com.unisinos.pareapp.model.dto.question.QuestionEntityDto;
import br.com.unisinos.pareapp.model.dto.session.SessionEntityDto;
import br.com.unisinos.pareapp.model.dto.solution.SolutionEntityDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.model.entity.*;
import com.github.roookeee.datus.api.Datus;
import com.github.roookeee.datus.api.Mapper;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class StandardConverters {

    public Mapper<Solution, SolutionEntityDto> solutionLoopConverter() {
        return Datus.forTypes(Solution.class,SolutionEntityDto.class).mutable(SolutionEntityDto::new)
                .from(BaseEntity::getId)
                    .into(BaseEntityDto::setId)
                .from(Solution::getImage)
                    .into(SolutionEntityDto::setImage)
                .build();
    }

    @Bean
    public Mapper<Solution, SolutionEntityDto> solutionStandardConverter() {
        return Datus.forTypes(Solution.class,SolutionEntityDto.class).mutable(SolutionEntityDto::new)
                .process((solution, solutionEntityDto) ->  solutionEntityDto = solutionLoopConverter().convert(solution))
                .from(Solution::getQuestion)
                    .map(question -> questionLoopConverter().convert(question))
                    .into(SolutionEntityDto::setQuestion)
                .build();
    }

    public Mapper<Question, QuestionEntityDto> questionLoopConverter() {
        return Datus.forTypes(Question.class, QuestionEntityDto.class).mutable(QuestionEntityDto::new)
                .from(BaseEntity::getId)
                    .into(BaseEntityDto::setId)
                .from(Question::getImage)
                    .into(QuestionEntityDto::setImage)
                .from(Question::getDescription)
                    .into(QuestionEntityDto::setDescription)
                .build();
    }

    @Bean
    public Mapper<Question, QuestionEntityDto> questionStandardConverter() {
        return Datus.forTypes(Question.class, QuestionEntityDto.class).mutable(QuestionEntityDto::new)
                .process((question, questionEntityDto) ->  questionEntityDto = questionLoopConverter().convert(question))
                .from(Question::getExercises)
                    .given(Objects::nonNull, exercises -> Sets.newHashSet(exerciseLoopConverter().convert(exercises)))
                    .orElse(Sets.newHashSet())
                    .into(QuestionEntityDto::setExercises)
                .from(Question::getSolution)
                    .given(Objects::nonNull, solution -> solutionLoopConverter().convert(solution))
                    .orElseNull()
                    .into(QuestionEntityDto::setSolution)
                .build();
    }

    public Mapper<Exercise, ExerciseEntityDto> exerciseLoopConverter() {
        return Datus.forTypes(Exercise.class, ExerciseEntityDto.class).mutable(ExerciseEntityDto::new)
                .from(BaseEntity::getId)
                    .into(BaseEntityDto::setId)
                .from(Exercise::getDescription)
                    .into(ExerciseEntityDto::setDescription)
                .build();
    }

    @Bean
    public Mapper<Exercise, ExerciseEntityDto> exerciseStandardConverter() {
        return Datus.forTypes(Exercise.class, ExerciseEntityDto.class).mutable(ExerciseEntityDto::new)
                .process((exercise, exerciseEntityDto) ->  exerciseEntityDto = exerciseLoopConverter().convert(exercise))
                .from(Exercise::getClassrooms)
                    .given(Objects::nonNull, classrooms -> Sets.newHashSet(classroomLoopConverter().convert(classrooms)))
                    .orElse(Sets.newHashSet())
                    .into(ExerciseEntityDto::setClassrooms)
                .from(Exercise::getQuestions)
                    .given(Objects::nonNull, questions -> Sets.newHashSet(questionLoopConverter().convert(questions)))
                    .orElse(Sets.newHashSet())
                    .into(ExerciseEntityDto::setQuestions)
                .from(Exercise::getSessions)
                    .given(Objects::nonNull, sessions -> Sets.newHashSet(sessionLoopConverter().convert(sessions)))
                    .orElse(Sets.newHashSet())
                    .into(ExerciseEntityDto::setSessions)
                .build();
    }


    public Mapper<Session, SessionEntityDto> sessionLoopConverter() {
        return Datus.forTypes(Session.class, SessionEntityDto.class).mutable(SessionEntityDto::new)
                    .from(BaseEntity::getId)
                    .into(BaseEntityDto::setId)
                .build();
    }

    @Bean
    public Mapper<Session, SessionEntityDto> sessionStandardConverter() {
        return Datus.forTypes(Session.class, SessionEntityDto.class).mutable(SessionEntityDto::new)
                .process((session, sessionEntityDto) ->  sessionEntityDto = sessionLoopConverter().convert(session))
                .from(Session::getExercise)
                    .map(exercise -> exerciseLoopConverter().convert(exercise))
                    .into(SessionEntityDto::setExercise)
                .from(Session::getPair)
                    .map(pair -> pairLoopConverter().convert(pair))
                    .into(SessionEntityDto::setPair)
                .from(Session::getAnswers)
                    .given(Objects::nonNull, answers -> Sets.newHashSet(answerLoopConverter().convert(answers)))
                    .orElse(Sets.newHashSet())
                    .into(SessionEntityDto::setAnswers)
                .build();
    }

    public Mapper<Answer, AnswerEntityDto> answerLoopConverter() {
        return Datus.forTypes(Answer.class, AnswerEntityDto.class).mutable(AnswerEntityDto::new)
                .from(BaseEntity::getId)
                    .into(BaseEntityDto::setId)
                .from(Answer::getImage)
                    .into(AnswerEntityDto::setImage)
                .build();
    }

    @Bean
    public Mapper<Answer, AnswerEntityDto> answerStandardConverter() {
        return Datus.forTypes(Answer.class, AnswerEntityDto.class).mutable(AnswerEntityDto::new)
                .process((answer, answerEntityDto) ->  answerEntityDto = answerLoopConverter().convert(answer))
                .from(Answer::getQuestion)
                    .map(question -> questionLoopConverter().convert(question))
                    .into(AnswerEntityDto::setQuestion)
                .from(Answer::getSession)
                    .map(session -> sessionLoopConverter().convert(session))
                    .into(AnswerEntityDto::setSession)
                .build();
    }

    public Mapper<Pair, PairEntityDto> pairLoopConverter() {
        return Datus.forTypes(Pair.class, PairEntityDto.class).mutable(PairEntityDto::new)
                .from(BaseEntity::getId)
                    .into(BaseEntityDto::setId)
                .build();
    }

    @Bean
    public Mapper<Pair, PairEntityDto> pairStandardConverter() {
        return Datus.forTypes(Pair.class, PairEntityDto.class).mutable(PairEntityDto::new)
                .process((pair, pairEntityDto) ->  pairEntityDto = pairLoopConverter().convert(pair))
                .from(Pair::getClassroom)
                    .map(classroom -> classroomLoopConverter().convert(classroom))
                    .into(PairEntityDto::setClassroom)
                .from(Pair::getStudent1)
                    .map(student1 -> userLoopConverter().convert(student1))
                    .into(PairEntityDto::setStudent1)
                .from(Pair::getStudent2)
                    .map(student2 -> userLoopConverter().convert(student2))
                    .into(PairEntityDto::setStudent2)
                .build();
    }

    public Mapper<Classroom, ClassroomEntityDto> classroomLoopConverter() {
        return Datus.forTypes(Classroom.class, ClassroomEntityDto.class).mutable(ClassroomEntityDto::new)
                .from(BaseEntity::getId)
                    .into(BaseEntityDto::setId)
                .from(Classroom::getName)
                    .into(ClassroomEntityDto::setName)
                .build();
    }

    @Bean
    public Mapper<Classroom, ClassroomEntityDto> classroomStandardConverter() {
        return Datus.forTypes(Classroom.class, ClassroomEntityDto.class).mutable(ClassroomEntityDto::new)
                .process((classroom, classroomEntityDto) ->  classroomEntityDto = classroomLoopConverter().convert(classroom))
                .from(Classroom::getOwner)
                    .map(owner -> userLoopConverter().convert(owner))
                    .into(ClassroomEntityDto::setOwner)
                .from(Classroom::getStudents)
                    .given(Objects::nonNull, students -> Sets.newHashSet(userLoopConverter().convert(students)))
                    .orElse(Sets.newHashSet())
                    .into(ClassroomEntityDto::setStudents)
                .from(Classroom::getExercises)
                    .given(Objects::nonNull, exercises -> Sets.newHashSet(exerciseLoopConverter().convert(exercises)))
                    .orElse(Sets.newHashSet())
                    .into(ClassroomEntityDto::setExercises)
                .from(Classroom::getPairs)
                    .given(Objects::nonNull, pairs -> Sets.newHashSet(pairLoopConverter().convert(pairs)))
                    .orElse(Sets.newHashSet())
                    .into(ClassroomEntityDto::setPairs)
                .build();
    }

    public Mapper<User, UserEntityDto> userLoopConverter() {
        return Datus.forTypes(User.class, UserEntityDto.class).mutable(UserEntityDto::new)
                .from(BaseEntity::getId)
                    .into(BaseEntityDto::setId)
                .from(User::getName)
                    .into(UserEntityDto::setName)
                .from(User::getUsername)
                    .into(UserEntityDto::setUsername)
                .build();
    }

    @Bean
    public Mapper<User, UserEntityDto> userStandardConverter() {
        return Datus.forTypes(User.class, UserEntityDto.class).mutable(UserEntityDto::new)
                .process((user, userEntityDto) ->  userEntityDto = userLoopConverter().convert(user))
                .from(User::getClassrooms)
                    .given(Objects::nonNull, classrooms -> Sets.newHashSet(classroomLoopConverter().convert(classrooms)))
                    .orElse(Sets.newHashSet())
                    .into(UserEntityDto::setClassrooms)
                .build();
    }
}
