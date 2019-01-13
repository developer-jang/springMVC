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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/boards/")
@Log
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/list")
    public void list(PageVO pageVO, Model model) {
        Pageable pageable = pageVO.makePageAble(0, "bno");
        log.info("" + pageable);

//        Predicate searchPredicate = boardRepository.makePredicate(null, null);
        Predicate searchPredicate = boardRepository.makePredicate(pageVO.getType(), pageVO.getKeyword());
        Page<Board> boardPage = boardRepository.findAll(searchPredicate, pageable);
        model.addAttribute("result", boardPage);
        model.addAttribute("result2", new PageMakerVO(boardPage));
    }

    @GetMapping("/register")
    public void registerGet(@ModelAttribute("vo")Board vo) {
        log.info("registerGet");
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("vo")Board vo, RedirectAttributes redirectAttributes) {
        boardRepository.save(vo);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/boards/list";
    }
}
