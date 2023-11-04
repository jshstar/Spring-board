package com.sparta.project_board.entity;


import com.sparta.project_board.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board")
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bNum;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "userName", nullable = false)
    private String userName;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;
    @Column(name = "pw", nullable = false)
    private String pw;

    // 게시글 작성
    public Board(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.userName = boardRequestDto.getUserName();
        this.contents = boardRequestDto.getContents();
        this.pw = boardRequestDto.getPw();
    }

    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.userName = requestDto.getUserName();
        this.contents = requestDto.getContents();
    }
}
