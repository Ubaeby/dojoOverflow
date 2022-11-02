package com.codingdojo.overflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.overflow.models.Question;
import com.codingdojo.overflow.models.Tag;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

	List<Question> findAll();
	List<Question> findAllByTags(Tag tag);
	List<Question> findByTagsNotContains(Tag tag);
	Question findByIdIs(Long id);
	
}
