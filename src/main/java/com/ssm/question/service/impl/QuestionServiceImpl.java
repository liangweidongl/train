package com.ssm.question.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.question.dao.QuestionDAO;
import com.ssm.question.pojo.Question;
import com.ssm.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDAO questionDAO;

    @Override
    public void addQuestion(Question question) {
        questionDAO.addQuestion(question);
    }

    @Override
    public PageInfo<Question> getQuestionList(Question question, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize, "UPDATE_TIME DESC");
        List<Question> questions = questionDAO.getQuestionList(question);
        PageInfo<Question> pageInfo = new PageInfo<>(questions, 3);
        return pageInfo;
    }

    @Override
    public Question getQuestionById(int id) {
        return questionDAO.getQuestionById(id);
    }
}
