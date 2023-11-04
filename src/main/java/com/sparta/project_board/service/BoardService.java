package com.sparta.project_board.service;

import com.sparta.project_board.dto.BoardRequestDto;
import com.sparta.project_board.dto.BoardResponseDto;
import com.sparta.project_board.entity.Board;
import com.sparta.project_board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시글 작성기능
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto)
    {
        // RequestDto = Entity
        Board board = new Board(boardRequestDto);
        //DB 저장
        Board saveBoard = boardRepository.save(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto(saveBoard);

        return boardResponseDto;
    }


    // 게시글 목록 조회
    public List<BoardResponseDto> getBoards(){
        //DB 조회
        return boardRepository.findAllByOrderByCreatedAtDesc()
                .stream().map(BoardResponseDto::new).toList();
    }

    // 선택한 게시글 조회 기능
    public BoardResponseDto selectGetBoard(Long bNum){
        Board board = findBoard(bNum);
        BoardResponseDto boardResponseDto = new BoardResponseDto(board);
        return boardResponseDto;
    }

    // 선택한 게시글 수정 기능
    @Transactional
    public BoardResponseDto updateBoard(Long bNum, BoardRequestDto boardRequestDto)
    {
        Board board = findBoard(bNum);
        BoardResponseDto boardResponseDto;

        // 여기서 Try catch로 잡아야되는건가 ?
        if(board.getPw().equals(boardRequestDto.getPw()))
        {
            board.update(boardRequestDto);
            boardResponseDto = new BoardResponseDto(board);
            return boardResponseDto;
        }
        else
        {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return null; // null 을 넣어도 되는가 ?
        }
    }

    // 선택한 게시글 삭제 기능 여기도 수정(비밀번호일치)
    public Long deleteMemo(Long bNum)
    {
        Board board = findBoard(bNum);
        boardRepository.delete(board);
        return bNum;
    }

    // 예외처리와 동시에 원하는 데이터값 출력
    private Board findBoard(Long bNum){
        return boardRepository.findById(bNum).orElseThrow(()->
            new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }

}
