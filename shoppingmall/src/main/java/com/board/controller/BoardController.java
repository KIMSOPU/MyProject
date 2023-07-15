package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVO;
import com.board.domain.Page;
import com.board.service.BoardService;

@Controller
@RequestMapping(value = "/board/")
public class BoardController {

	@Autowired
	BoardService boardService;

	// 게시물 리스트 조회
	@RequestMapping(value = "/list")
	public String getList(Model model) throws Exception {

		List<BoardVO> list = boardService.getList();
		model.addAttribute("list", list);

		return "board/boardList";
	}

	// 게시물 작성
	@GetMapping("/write")
	public void getWrite() throws Exception {

	}

	// 게시물 작성
	@PostMapping("/write")
	public String postWrite(BoardVO boardVO) throws Exception {
		boardService.write(boardVO);
		// 모든 작업을 마치고 다시 /board/list, 즉 게시물 목록 화면으로 되돌아가겠다.
		return "redirect:/board/boardList";
	}

	// 게시물 상세조회
	@GetMapping("/view")
	public void getView(@RequestParam("bno") int bno, Model model, BoardVO badVO) throws Exception {

		BoardVO vo = boardService.view(bno);
		// 게시물 조회수 증가
		// boardService.setViewCnt(badVO);

		model.addAttribute("view", vo);

	}

	// 게시물 수정
	@GetMapping("/modify")
	public void getModify(@RequestParam("bno") int bno, Model model) throws Exception {

		BoardVO boardVO = boardService.view(bno);
		model.addAttribute("view", boardVO);
	}

	// 게시물 수정2
	@PostMapping("/modify")
	public String postModify(BoardVO vo) throws Exception {

		boardService.modify(vo);
		return "redirect:/board/view?bno=" + vo.getBno();
	}

	// 게시물 삭제
	@GetMapping("/delete")
	public String getDelete(@RequestParam("bno") int bno) throws Exception {

		boardService.delete(bno);

		return "redirect:/board/boardList";
	}

	// 게시물 목록 + 페이징 추가
	@GetMapping("/listpage")
	public void getListPage(Model model) throws Exception {

		List<BoardVO> list = boardService.getList();
		model.addAttribute("list", list);
	}

	// 게시물 목록 + 페이징 추가
	@GetMapping("/listPage")
	public void getListPage(Model model, @RequestParam("num") int num) throws Exception {

		Page page = new Page();

		page.setNum(num);
		page.setCount(boardService.count());

		List<BoardVO> list = null;
		list = boardService.listPage(page.getDisplayPost(), page.getPostNum());

		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("select", num);

	}
	
	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listPageSearch", method = RequestMethod.GET)
	public void getListPageSearch(Model model, @RequestParam("num") int num) throws Exception {

		Page page = new Page();

		page.setNum(num);
		page.setCount(boardService.count());

		List<BoardVO> list = null;
		list = boardService.listPage(page.getDisplayPost(), page.getPostNum());

		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("select", num);

	}
	

}