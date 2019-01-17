package com.example.springmvc.demo.repository;

import com.example.springmvc.demo.domain.Board;
import com.example.springmvc.demo.domain.Reply;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertBoardDummiesData() {
        Long[] arr = {304L, 303L, 300L};

        Arrays.stream(arr).forEach(num -> {
            Board board = new Board();
            board.setBno(num);

            IntStream.range(0, 10).forEach(i -> {
                Reply reply = new Reply();
                reply.setReplyText("Reply..." + i);
                reply.setReplyer("replyer" + i);
                reply.setBoard(board);
                replyRepository.save(reply);
            });

        });
    }
}
