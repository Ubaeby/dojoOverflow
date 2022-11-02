package com.codingdojo.overflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.overflow.models.Answer;
import com.codingdojo.overflow.models.Question;
import com.codingdojo.overflow.models.Tag;
import com.codingdojo.overflow.repositories.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository qRepo;
	
	public List<Question> allQuestions() {
		return qRepo.findAll();
	}
	
	public List<Question> assignedTag(Tag t) {
		return qRepo.findAllByTags(t);
	}
	
	public List<Question> unassignedTag(Tag t) {
		return qRepo.findByTagsNotContains(t);
	}
	
	public Question findById(Long id) {
		Optional<Question> optionQ = qRepo.findById(id);
		if(optionQ.isPresent()) {
			return optionQ.get();
		}
		return null;
	}
	
	public Question addQuestion(Question q) {
		return qRepo.save(q);
	}
	
	public Question addAnswer(Question question, Answer answer) {
		question.getAnswers().add(answer);
		return qRepo.save(question);
	}
	
}
