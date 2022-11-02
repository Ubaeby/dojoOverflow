package com.codingdojo.overflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.overflow.models.Question;
import com.codingdojo.overflow.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

	List<Tag> findAll();
	List<Tag> findAllByQuestions(Question question);
	List<Tag> findByQuestionsNotContains(Question question);
	Tag findByIdIs(Long id);
	Tag findBySubject(String subject);
}
