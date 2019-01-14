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

    @GetMapping("/view")
    public void view(Long bno, @ModelAttribute("pageVO") PageVO pageVO, Model model) {
        boardRepository.findById(bno).ifPresent(board -> model.addAttribute("vo", board));
    }

    @GetMapping("/modify")
    public void modify(Long bno, @ModelAttribute("pageVO") PageVO pageVO, Model model) {
        boardRepository.findById(bno).ifPresent(board -> model.addAttribute("vo", board));
    }

    @PostMapping("/modify")
    public String modifyPost(Board board, PageVO pageVO, RedirectAttributes redirectAttributes) {
        boardRepository.findById(board.getBno()).ifPresent(origin -> {
            origin.setTitle(board.getTitle());
            origin.setContent(board.getContent());
            boardRepository.save(origin);
            redirectAttributes.addFlashAttribute("msg", "success");
            redirectAttributes.addFlashAttribute("bno", origin.getBno());
        });
        redirectAttributes.addAttribute("page", pageVO.getPage());
        redirectAttributes.addAttribute("size", pageVO.getSize());
        redirectAttributes.addAttribute("type", pageVO.getType());
        redirectAttributes.addAttribute("keyword", pageVO.getKeyword());
        return "redirect:/boards/view";
    }

    @PostMapping("/delete")
    public String delete(Long bno, PageVO pageVO, RedirectAttributes redirectAttributes) {

        /*
         * RedirectAttributes의 필요성
         * rediect:${원하는 페이지주소}로 화면을 돌리면되나, 데이터를 넘겨주어야 할떄, addFlashAttribute를 사용한다.
         * */
        boardRepository.deleteById(bno);
        redirectAttributes.addFlashAttribute("msg", "success");
        redirectAttributes.addFlashAttribute("page", pageVO.getPage());
        redirectAttributes.addFlashAttribute("size", pageVO.getSize());
        redirectAttributes.addFlashAttribute("type", pageVO.getType());
        redirectAttributes.addFlashAttribute("keyword", pageVO.getKeyword());

        return "redirect:/boars/list";
    }
}
