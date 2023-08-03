package com.malak.BoardAPI.Controller;

import com.malak.BoardAPI.Models.board;
import com.malak.BoardAPI.Models.card;
import com.malak.BoardAPI.Service.boardService;
import com.malak.BoardAPI.Service.cardService;
import com.malak.BoardAPI.responseObject.boardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
//for testing
import com.malak.BoardAPI.Repositry.boardRepositry;
import com.malak.BoardAPI.Repositry.cardRepositry;
@RestController
@RequestMapping (path = "/api")
public class cardController {
    @Autowired
   boardService boardservice;

    @Autowired
    cardService cardaservice;
    //for testing
    @Autowired
    boardRepositry boardRepo;
    @Autowired
    cardRepositry cardRepo;


    @GetMapping
    List<card> getSubjects() {
        return cardRepo.findAll();
    }

    @PostMapping
    card createSubject(/*@RequestBody card subject*/) {

        card c = new card();
        c.setCreatedDate(new Date());
        c.setIsActive(true);
        c.setTitle("malak 1st card");
        c.setDescription("this card must work");
        return cardRepo.save(c);
    }

//    @PutMapping("/{subjectId}/students/{studentId}")
//    card addStudentToSubject(
//            @PathVariable Long subjectId,
//            @PathVariable Long studentId
//    ) {
//        Subject subject = subjectRepository.findById(subjectId).get();
//        Student student = studentRepository.findById(studentId).get();
//        subject.enrolledStudents.add(student);
//        return subjectRepository.save(subject);
//    }

    @PutMapping("/{subjectId}/teacher/{teacherId}")
    board assignTeacherToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long teacherId
    ) {

        Set<card> projectSet = null;
        card card = cardRepo.findById(subjectId).get();
        board board = boardRepo.findById(teacherId).get();
        projectSet =  board.getCards();
        projectSet.add(card);
        board.setCards(projectSet);
        return boardRepo.save(board);


    }



}
