package com.sparta.project_board.dto;


import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String userName;
    private String pw;
    private String contents;
}
