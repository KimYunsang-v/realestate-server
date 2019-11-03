package kr.ac.skuniv.realestate.repository.custom;

import kr.ac.skuniv.realestate.domain.dto.boardDto.AnswerSaveDto;
import kr.ac.skuniv.realestate.domain.entity.Answer;
import kr.ac.skuniv.realestate.domain.entity.Board;

import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> findByAddressAndPage(String city, String district, int page);

//    Board saveAnswer(Answer answer, Long no);
}
