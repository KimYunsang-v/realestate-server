package kr.ac.skuniv.realestate.service;

import kr.ac.skuniv.realestate.domain.dto.boardDto.*;
import kr.ac.skuniv.realestate.domain.entity.Answer;
import kr.ac.skuniv.realestate.domain.entity.Board;
import kr.ac.skuniv.realestate.exception.UserDefineException;
import kr.ac.skuniv.realestate.repository.AnswerRepository;
import kr.ac.skuniv.realestate.repository.BoardRepository;
import kr.ac.skuniv.realestate.repository.impl.BoardRepositoryImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by YoungMan on 2019-02-16.
 */

@Service @Log4j2
public class BoardService {

    private final BoardRepository boardRepository;
    private final AnswerRepository answerRepository;

    public BoardService(BoardRepository boardRepository, AnswerRepository answerRepository) {
        this.boardRepository = boardRepository;
        this.answerRepository = answerRepository;
    }
//
//    public Board getBoardById(Long boardNo) {
//        return boardRepository.findById(boardNo).orElseThrow(() -> new UserDefineException("해당 No의 게시글이 없습니다."));
//    }
//
//    public Answer getAnswerById(Long answerNo) {
//        return answerRepository.findById(answerNo).orElseThrow(() -> new UserDefineException("해당 No의 게시글이 없습니다."));
//    }

    public List<Board> getBoardsByPage(String city, String district, Integer page) {
        //PageRequest pageRequest = new PageRequest(pageNum - 1, 20, Sort.Direction.DESC, "no");
        List<Board> boards = boardRepository.findByAddressAndPage(city, district, page);
        log.info("============get board size : " + boards.size() );
        return boards;
    }

    public Board saveBoard(BoardSaveDto boardSaveDto) {
        return boardRepository.save(convertDtoToSaveBoard(boardSaveDto));
    }

    public Board saveAnswer(AnswerSaveDto answerSaveDto) {

        Answer answer = buildAnswer(answerSaveDto);

        Board board = boardRepository.findById(answerSaveDto.getBoardNo()).get();

        answer.setBoard(board);

        answerRepository.save(answer);

        board.getAnswers().add(answer);
        //board.getAnswers().add(answer);

//        Board board1 = boardRepository.findById(answerSaveDto.getBoardNo()).get();

//        log.warn(board1.toString());

        return board;
    }

    private Answer buildAnswer(AnswerSaveDto answerSaveDto) {
        return Answer.builder().author(answerSaveDto.getAuthor()).content(answerSaveDto.getContent()).build();
    }

//    public List<Board> getBoardsByTitle(String title) {
//        return boardRepository.getBoardsByTitle(title);
//    }
//
//    public Board saveBoard(BoardSaveDto boardSaveDto) {
//        return boardRepository.save(convertDtoToSaveBoard(boardSaveDto));
//    }
//
    private Board convertDtoToSaveBoard(BoardSaveDto boardSaveDto) {
        return Board.builder()
                .title(boardSaveDto.getTitle())
                .content(boardSaveDto.getContent())
                .author(boardSaveDto.getAuthor())
                .city(boardSaveDto.getCity())
                .district(boardSaveDto.getDistrict())
                .build();
    }
//
//    public Board updateBoard(BoardUpdateDto boardUpdateDto) {
//        return boardRepository.save(convertDtoToUpdateBoard(boardUpdateDto));
//    }
//
//    private Board convertDtoToUpdateBoard(BoardUpdateDto boardUpdateDto) {
//        Board board = getBoardById(boardUpdateDto.getNo());
//        board.setTitle(boardUpdateDto.getTitle());
//        board.setContent(boardUpdateDto.getContent());
//        return board;
//    }
//
//    public void deleteBoard(Long boardNo) {
//        boardRepository.deleteById(boardNo);
//    }
//
//    public Answer saveAnswer(AnswerSaveDto answerSaveDto) {
//        return answerRepository.save(convertDtoToSaveAnswer(answerSaveDto));
//    }
//
//    private Answer convertDtoToSaveAnswer(AnswerSaveDto answerSaveDto) {
//        Board board = getBoardById(answerSaveDto.getBoardNo());
//
//        return Answer.builder()
//                .content(answerSaveDto.getContent())
//                .author(answerSaveDto.getAuthor())
//                .board(board)
//                .build();
//    }
//
//    public Answer updateAnswer(AnswerUpdateDto answerUpdateDto) {
//        return answerRepository.save(convertDtoToUpdateAnswer(answerUpdateDto));
//    }
//
//    private Answer convertDtoToUpdateAnswer(AnswerUpdateDto answerUpdateDto) {
//        Answer answer = getAnswerById(answerUpdateDto.getNo());
//        answer.setContent(answerUpdateDto.getContent());
//        return answer;
//    }
//
//    public void deleteAnswer(Long answerNo) {
//        answerRepository.deleteById(answerNo);
//    }

}
