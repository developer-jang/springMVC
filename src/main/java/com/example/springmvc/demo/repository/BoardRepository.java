package com.example.springmvc.demo.repository;

import com.example.springmvc.demo.domain.Board;
import com.example.springmvc.demo.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {

     default Predicate makePredicate(String type, String keyword) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QBoard board = QBoard.board;
        booleanBuilder.and(board.bno.gt(0));

        if(type == null) {
           return booleanBuilder;
        }

        switch (type) {
            case "t" :
                booleanBuilder.and(board.title.contains(keyword));
                break;
            case "c" :
                booleanBuilder.and(board.content.contains(keyword));
                break;
            case "w" :
                booleanBuilder.and(board.writer.contains(keyword));
                break;


        }
        return booleanBuilder;
    }
}
