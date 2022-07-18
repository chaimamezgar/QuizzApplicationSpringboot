package com.ecole.api;

import com.ecole.domain.Examen;
import com.ecole.domain.Question;
import com.ecole.repository.UserRepository;
import com.ecole.service.ExamenService;
import com.ecole.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/question")
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamenService examenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value="/prof/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.saveQuestion(question));
    }

    @PutMapping(value="/prof/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.saveQuestion(question));
    }

    @GetMapping("/etudiant/{examenId}")
    public ResponseEntity<?> getAllQuestion(@PathVariable("examenId") Long examenId) throws Exception{
        Examen examen=this.examenService.getExamenById(examenId);
        Set<Question> questions=examen.getQuestions();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAllQuestions() throws Exception{

        Set<Question> questions=this.questionService.getQuestions();
        List<Question> q=new ArrayList<>(questions);
        return ResponseEntity.ok(q);
    }

    @GetMapping("/admin/ById/{quesId}")
    public Question getQuestion(@PathVariable("quesId") Long quesId) throws Exception {
        Question question=this.questionService.getQuestionById(quesId);
        System.out.println(" enonce de la question:"+question.getEnonce());
        return this.questionService.getQuestionById(quesId);
    }

    @DeleteMapping("/admin/{idques}")
    public ResponseEntity<?>deleteQuestion(@PathVariable("idques") Long idques) throws Exception {
        System.out.println("question to be deleted with request question id is: "+idques);
        Question question=this.questionService.getQuestionById(idques);
        if(question==null) {
            throw new Exception("Question not found exception");
        }
        this.questionService.deletequestion(idques);

        return ResponseEntity.ok(question);
    }

}
