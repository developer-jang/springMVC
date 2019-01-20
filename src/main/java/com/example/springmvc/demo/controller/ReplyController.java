package com.example.springmvc.demo.controller;

import com.example.springmvc.demo.domain.Board;
import com.example.springmvc.demo.domain.Reply;
import com.example.springmvc.demo.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/replies/")
public class ReplyController {

    @Autowired
    private ReplyRepository replyRepository;

    //    save
    @PostMapping("/{bno}")
    public ResponseEntity<List<Reply>> add(@PathVariable("bno") Long bno, @RequestBody Reply reply) {
        Board board = new Board();
        board.setBno(bno);

        reply.setBoard(board);
        replyRepository.save(reply);
        return new ResponseEntity<>(getReplyListByBno(board), (HttpStatus.CREATED));
    }

    private List<Reply> getReplyListByBno(Board board) {
//        return replyRepository.fin
        return new ArrayList<>();
    }

    @Transactional
    @DeleteMapping("/{bno}/{rno}")
    public ResponseEntity<List<Reply>> remove(@PathVariable("bno") Long bno, @PathVariable("rno") Long rno) {
        replyRepository.deleteById(rno);
        Board board = new Board();
        board.setBno(bno);
        return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/{bno}")
    public ResponseEntity<List<Reply>> modify(@PathVariable("bno") Long bno, @RequestBody Reply reply) {
        replyRepository.findById(reply.getRno()).ifPresent(origin -> {
            origin.setReplyText(reply.getReplyText());
            replyRepository.save(origin);
        });
        Board board = new Board();
        board.setBno(bno);
        return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
    }

    private List<Reply> getListByBoard(Board board) {
        return new ArrayList<>();
    }
}
