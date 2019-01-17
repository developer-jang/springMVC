package com.example.springmvc.demo.repository;

import com.example.springmvc.demo.domain.Reply;
import org.springframework.data.repository.CrudRepository;

public interface ReplyRepository extends CrudRepository<Reply, Long> {
}
