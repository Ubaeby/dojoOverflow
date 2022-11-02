package com.codingdojo.overflow.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="answers")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="If you want to answer, at least provide an answer")
	@Size(min=3, max = 255, message="Answer must have at least 3 characters to it!")
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="question_id")
	private Question question;
	
	public Answer() {}
	
	public Answer(String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Question getQuestion() {
		return question;
	}	

	public void setQuestion(Question question) {
		this.question = question;
	};
	
	
}
