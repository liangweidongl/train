package com.ssm.question.dao;

import com.ssm.question.pojo.Answer;

import java.util.List;

public interface AnswerDAO {
    void addAnswer(Answer answer);

    List<Answer> getAnswerList(int questionId);

    void deleteAnswer(int id);
}
