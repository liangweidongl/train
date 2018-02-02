package com.ssm.question.service;

import com.ssm.question.pojo.Answer;

import java.util.List;

public interface AnswerService {
    void addAnswer(Answer answer);

    List<Answer> getAnswerList(int questionId);

    void deleteAnswer(int id,int questionId);
}
