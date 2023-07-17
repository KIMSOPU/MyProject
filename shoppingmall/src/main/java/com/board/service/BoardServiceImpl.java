package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;

	// 게시물 리스트 조회
	@Override
	public List<BoardVO> getList() throws Exception {

		return boardDAO.getList();
	}

	// 게시물 작성
	@Override
	public void write(BoardVO vo) throws Exception {

		boardDAO.write(vo);
	};

	// 게시물 상세조회
	@Override
	public BoardVO view(int bno) throws Exception {

		return boardDAO.view(bno);
	}

	// 게시물 수정
	@Override
	public void modify(BoardVO vo) throws Exception {

		boardDAO.modify(vo);
	}

	// 게시물 삭제
	@Override
	public void delete(int bno) throws Exception {
		boardDAO.delete(bno);
	}

	// 게시물 조회수 증가
	/*
	 * @Override public void setViewCnt(BoardVO badVO) { boardDAO.setViewCnt(badVO);
	 * }
	 */

	//게시물 총 갯수
	@Override
	public int count() throws Exception {
		return boardDAO.count();
	}

	// 게시물 목록 + 페이징
	@Override
	public List<BoardVO> listPage(int displayPost, int postNum) throws Exception {
		return boardDAO.listPage(displayPost, postNum);
	}

	// 게시물 목록 + 페이징 + 검색
	@Override
	public List<BoardVO> listPageSearch(int displayPost, int postNum, String searchType, String keyword)
			throws Exception {
		return boardDAO.listPageSearch(displayPost, postNum, searchType, keyword);
	}
}
