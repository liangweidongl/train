package com.ssm.question.controller;

import com.ssm.question.pojo.Answer;
import com.ssm.question.pojo.User;
import com.ssm.question.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping("add.html")
    @ResponseBody
    public Map<String, Object> addAnswer(Answer answer, HttpSession session) {
        User user = (User) session.getAttribute("SESSION_USER");
        answer.setUser(user);
        answerService.addAnswer(answer);
        Map<String, Object> result = new HashMap<>();
        result.put("status", true);
        result.put("message", "回答成功");
        result.put("answer", answer);
        return result;
    }

    @RequestMapping("delete.html")
    @ResponseBody
    public Map<String, Object> delete(Integer id,Integer questionId) {
        answerService.deleteAnswer(id,questionId);
        Map<String, Object> result = new HashMap<>();
        result.put("status", true);
        result.put("message", "删除成功");
        return result;
    }
}
