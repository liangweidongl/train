package com.ssm.question.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.question.pojo.Answer;
import com.ssm.question.pojo.Question;
import com.ssm.question.pojo.User;
import com.ssm.question.service.AnswerService;
import com.ssm.question.service.QuestionService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Value("${UPLOAD_DIR}")
    private String UPLOAD_DIR;

    @Value("${IMAGE_URL}")
    private String IMAGE_URL;

    @RequestMapping("/upload.html")
    @ResponseBody
    public String upload(@RequestParam(name = "file") MultipartFile file) {
        try {
            String oldFileName = file.getOriginalFilename();//aaa.jpg
            //.jpg
            String extName = oldFileName.substring(oldFileName.lastIndexOf("."));
            String fileName = System.currentTimeMillis() + extName;//20123432532.jpg
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(UPLOAD_DIR + fileName));
            String json = "{\"error\":0,\"url\":\"" + IMAGE_URL + fileName + "\"}";
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{\"error\":1,\"url\":\"\"}";
    }

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/add.html")
    public String add(Question question, HttpSession session) {
        User user = (User) session.getAttribute("SESSION_USER");
        question.setUser(user);
        questionService.addQuestion(question);
        return "redirect:/question/list.html";
    }

    @RequestMapping("list.html")
    public ModelAndView list(Question question, @RequestParam(defaultValue = "1") Integer pageNum) {
        PageInfo<Question> pageInfo = questionService.getQuestionList(question, pageNum, 2);
        return new ModelAndView("question_list", "pageInfo", pageInfo);
    }

    @Autowired
    private AnswerService answerService;

    @RequestMapping("detail.html")
    public String detail(Integer id, Model model) {
        Question question = questionService.getQuestionById(id);
        model.addAttribute("question", question);
        List<Answer> answers = answerService.getAnswerList(id);
        model.addAttribute("answers", answers);
        return "question_detail";
    }
}
