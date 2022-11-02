package com.codingdojo.overflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.overflow.models.Question;
import com.codingdojo.overflow.models.Tag;
import com.codingdojo.overflow.repositories.TagRepository;

@Service
public class TagService {

	@Autowired
	private TagRepository tag;
	
	public List<Tag> allTags() {
		return tag.findAll();
	}
	
	public List<Tag> assignedQuestion(Question q) {
		return tag.findAllByQuestions(q);
	}
	
	public List<Tag> unassignedQuestion(Question q) {
		return tag.findByQuestionsNotContains(q);
	}
	
	public Tag findById(Long id) {
		Optional<Tag> option = tag.findById(id);
		if (option.isPresent()) {
			return option.get();
		}
		return null;
	}
	
	public Tag findBySubject(String s) {
		return tag.findBySubject(s);
	}
	
	public Tag addTag(Tag t) {
		return tag.save(t);
	}
}
