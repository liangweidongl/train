package com.ssm.question.service;

import com.github.pagehelper.PageInfo;
import com.ssm.question.pojo.Question;

public interface QuestionService {
    void addQuestion(Question question);

    PageInfo<Question> getQuestionList(Question question, int pageNo, int pageSize);

    Question getQuestionById(int id);
}
