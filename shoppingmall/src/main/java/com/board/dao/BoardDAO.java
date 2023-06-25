package com.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardVO;

@Repository
public class BoardDAO {

	@Inject
	private SqlSession sql;
	private static String namespace = "com.board.mappers.board";

	// 게시판 목록
	public List<BoardVO> list() throws Exception {

		return sql.selectList(namespace + ".list");
	};

	// 게시물 작성
	public void write(BoardVO vo) throws Exception {
		
		sql.insert(namespace + ".write", vo);
	}

	// 게시물 조회
	public BoardVO view(int bno) throws Exception {

		return sql.selectOne(namespace + ".view", bno);
	}

	// 게시물 수정
	public void modify(BoardVO vo) throws Exception {
		
		sql.update(namespace + ".modify", vo);
	};
	
	// 게시물 삭제
	public void delete(int bno) throws Exception {
	 sql.delete(namespace + ".delete", bno);
	}
	// 게시물 조회수 증가
	/*
	 * public void setViewCnt(BoardVO badVO) { sql.insert(namespace + ".setViewCnt",
	 * badVO); }
	 */
}
