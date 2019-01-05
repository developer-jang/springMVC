package com.example.springmvc.demo.controller;

import com.example.springmvc.demo.domain.Board;
import com.example.springmvc.demo.repository.BoardRepository;
import com.example.springmvc.demo.vo.PageMakerVO;
import com.example.springmvc.demo.vo.PageVO;
import com.querydsl.core.types.Predicate;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards/")
@Log
public class SampleBoardController {

//    @GetMapping("/list")
//    public void list() {
//        log.info("list() called");
//    }

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/list")
    public void list(PageVO pageVO, Model model) {
        Pageable pageable = pageVO.makePageAble(0, "bno");
        log.info("" + pageable);

        Predicate searchPredicate = boardRepository.makePredicate(null, null);
        Page<Board> boardPage = boardRepository.findAll(searchPredicate, pageable);

///*
//
// Using ASTQueryTranslatorFactory
//Hibernate: select board0_.bno as bno1_0_, board0_.content as content2_0_, board0_.reg_dt as reg_dt3_0_, board0_.title as title4_0_, board0_.up_dt as up_dt5_0_, board0_.writer as writer6_0_ from board board0_ where board0_.bno>? order by board0_.bno desc limit ?
//Hibernate: select count(board0_.bno) as col_0_0_ from board board0_ where board0_.bno>?
//
//
// */
        model.addAttribute("result", boardPage);
        model.addAttribute("result2", new PageMakerVO(boardPage));
    }
}
