package com.codingdojo.overflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.overflow.models.Answer;
import com.codingdojo.overflow.models.Question;
import com.codingdojo.overflow.repositories.AnswerRepository;

// This is a ManyToOne relationship with Questions
@Service
public class AnswerService {

	@Autowired
	private AnswerRepository answer;
	
	public List<Answer> allAnswers() {
		return answer.findAll();
	}
	
	public Answer addAnswer(Answer a) {
		return answer.save(a);
	}
	
	public Answer findAnswer(Long id) {
		Optional<Answer> option = answer.findById(id);
		if (option.isPresent()) {
			return option.get();
		}
		return null;
	}
	
	public List<Answer> whichQuestion(Question q) {
		return answer.findAllByQuestion(q);
	}
}
