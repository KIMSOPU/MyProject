package com.board.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVO;
import com.board.service.BoardService;

@Controller
@RequestMapping(value = "/board/")
public class BoardController {

	@Autowired
	BoardService service;

	// 게시물 리스트 조회
	@RequestMapping(value = "/list")
	public String getList(Model model) throws Exception {

		List<BoardVO> list = service.list();
		model.addAttribute("list", list);

		return "board/list";
	}

	// 게시물 작성
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWrite() throws Exception {

	}

	// 게시물 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWrite(BoardVO vo) throws Exception {
		service.write(vo);
		// 모든 작업을 마치고 다시 /board/list, 즉 게시물 목록 화면으로 되돌아가겠다.
		return "redirect:/board/list";
	}

	// 게시물 상세조회
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("bno") int bno, Model model, BoardVO badVO) throws Exception {

		BoardVO vo = service.view(bno);
		// 게시물 조회수 증가 
		// service.setViewCnt(badVO);
		
		model.addAttribute("view", vo);
		
	}

	// 게시물 수정
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify(@RequestParam("bno") int bno, Model model) throws Exception {

		BoardVO vo = service.view(bno);
		model.addAttribute("view", vo);
	}

	// 게시물 수정2
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(BoardVO vo) throws Exception {

		service.modify(vo);
		return "redirect:/board/view?bno=" + vo.getBno();
	}

	// 게시물 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(@RequestParam("bno") int bno) throws Exception {
	  
	 service.delete(bno);  

	 return "redirect:/board/list";
	}
	
}