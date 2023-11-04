package com.sparta.project_board.entity;

import com.sparta.project_board.repository.BoardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BoardTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    BoardRepository boardRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("게시글 작성 완료")
    void test1() {

        Board board = new Board();
        board.setTitle("test title");
        board.setUserName("user1");
        board.setContents("test 입니다");
        board.setPw("1234");
        em.persist(board);
    }
}