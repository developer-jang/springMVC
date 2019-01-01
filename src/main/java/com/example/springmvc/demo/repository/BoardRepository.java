package com.example.springmvc.demo.repository;

import com.example.springmvc.demo.domain.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {
}
