package com.codingdojo.overflow.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.overflow.models.Answer;
import com.codingdojo.overflow.models.Question;
import com.codingdojo.overflow.models.Tag;
import com.codingdojo.overflow.services.AnswerService;
import com.codingdojo.overflow.services.QuestionService;
import com.codingdojo.overflow.services.TagService;

@Controller
public class MainController {

	@Autowired
	private QuestionService qServ;
	@Autowired
	private TagService tServ;
	@Autowired
	private AnswerService aServ;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/questions";
	}
	
	@GetMapping("/questions")
	public String main(Model model) {
		model.addAttribute("questions", qServ.allQuestions());
		model.addAttribute("tags", tServ.allTags());		
		return "dashboard.jsp";
	}
	
	
	@GetMapping("/questions/new") 
	public String questionPage(
			@ModelAttribute("newQuestion") Question newQuestion, Model model) {
		model.addAttribute("tags", tServ.allTags());
		return "newQuestions.jsp";
	}
	
	
	
	@PostMapping("/questions/new")
	public String addQuestion(
			@Valid @ModelAttribute("newQuestion") Question newQuestion, BindingResult result,
			@RequestParam(value="allTags") String allTags, Model model) {
		if (result.hasErrors()) {
			return "newQuestions.jsp";
		}
		else {
			List<Tag> tagQuestions = checkTag(allTags);
			if(tagQuestions != null) {
				Question makeQuestion = new Question(newQuestion.getText());
				makeQuestion.setTags(tagQuestions);
				qServ.addQuestion(makeQuestion);
			}
			else {
				model.addAttribute("error", "Please ensure all tags are lowercase, separated by a comma, and less than 3 tags!");
				return "newQuestions.jsp"; 
		}
			model.addAttribute("tags", tServ.allTags());
			return "redirect:/";
	}
	}
	
	@GetMapping("/questions/{id}")
	public String showQuestion(
			@PathVariable("id") Long id, @ModelAttribute("newAnswer") Answer newAnswer, Model model) {
		model.addAttribute("questions", qServ.findById(id));
		model.addAttribute("aTags", tServ.assignedQuestion(qServ.findById(id)));
		model.addAttribute("answers", aServ.allAnswers());
		
		return "input.jsp";
	}
	
	@PostMapping("/questions/{id}")
	public String answerCreate(
			@PathVariable("id") Long id,
			@Valid @ModelAttribute("newAnswer") Answer newAnswer, BindingResult result, Model model) {
		model.addAttribute("questions", qServ.findById(id));
		if (result.hasErrors()) {
			return "input.jsp";
		}
		Answer answerQuestion = new Answer(newAnswer.getText());
		answerQuestion.setQuestion(qServ.findById(id));
		aServ.addAnswer(answerQuestion);
		return "redirect:/questions/" + id;
	}
	
	
	private List<Tag> checkTag(String tagChecker) {
		String[] sepTag = tagChecker.trim().split(",");
		ArrayList<Tag> tagHold = new ArrayList<>();
		if (sepTag.length > 3) {
			return null;
		}
		for (String s : sepTag) {
			s = s.trim().toLowerCase();
			if (tServ.findBySubject(s) == null && s.length()>0) {
				tagHold.add(new Tag(s));
			}
			else {
				tagHold.add(tServ.findBySubject(s));
			}
		}
		
		return tagHold;
	}
	
	
}
