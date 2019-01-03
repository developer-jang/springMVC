package com.example.springmvc.demo.controller;

import com.example.springmvc.demo.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/list")
    public void list(PageVO pageVO) {
        Pageable pageable = pageVO.makePageAble(0, "bno");
        log.info("" + pageable);
    }
}
