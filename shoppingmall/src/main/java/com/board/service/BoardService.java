package com.board.service;

import java.util.List;

import com.board.domain.BoardVO;

public interface BoardService {

	List<BoardVO> list() throws Exception;

}
