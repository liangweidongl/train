package com.ssm.question.service.impl;

import com.ssm.question.dao.AnswerDAO;
import com.ssm.question.dao.QuestionDAO;
import com.ssm.question.pojo.Answer;
import com.ssm.question.pojo.Question;
import com.ssm.question.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerDAO answerDAO;

    @Autowired
    private QuestionDAO questionDAO;

    @Override
    public void addAnswer(Answer answer) {
        answer.setCreatedTime(new Date());
        answerDAO.addAnswer(answer);
        Question question = new Question();
        question.setId(answer.getQuestionId());
        question.setReplyCount(1);
        questionDAO.updateCountAndTime(question);
    }

    @Override
    public List<Answer> getAnswerList(int questionId) {
        return answerDAO.getAnswerList(questionId);
    }

    @Override
    public void deleteAnswer(int id,int questionId) {
        answerDAO.deleteAnswer(id);
        Question question = new Question();
        question.setId(questionId);
        question.setReplyCount(-1);
        questionDAO.updateCountAndTime(question);
    }
}
