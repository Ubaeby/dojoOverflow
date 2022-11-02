package com.codingdojo.overflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.overflow.models.Answer;
import com.codingdojo.overflow.models.Question;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {

	List<Answer> findAll();
	List<Answer> findAllByQuestion(Question question);
}
