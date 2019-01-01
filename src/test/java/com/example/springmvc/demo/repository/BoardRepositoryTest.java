package com.example.springmvc.demo.repository;

import com.example.springmvc.demo.domain.Board;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoardDummiesData() {
        IntStream.range(0, 300).forEach(i -> {
            Board board = new Board();

            board.setTitle("Sample Board Title " + i);
            board.setContent("Content Sample " + i + " of Board");
            board.setWriter("user0" + (i % 10));

            boardRepository.save(board);
        });
    }
}
