package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	/* 
	 // 게시물 총 갯수
	 int count = boardService.count();
	  
	 // 한 페이지에 출력할 게시물 갯수
	 int postNum = 10;
	  
	 // 하단 페이징 번호 ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
	 int pageNum = (int)Math.ceil((double)count/postNum);
	  
	 // 출력할 게시물
	 int displayPost = (num - 1) * postNum;
	 
	 // 한번에 표시할 페이징 번호의 갯수
	 int pageNum_cnt = 10;
	 
	 // 표시되는 페이지 번호 중 마지막 번호
	 int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
	   
	 // 표시되는 페이지 번호 중 첫번째 번호
	 int startPageNum = endPageNum - (pageNum_cnt - 1);
	 
	// 마지막 번호 재계산
	 int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));
	  
	 if(endPageNum > endPageNum_tmp) {
	  endPageNum = endPageNum_tmp;
	 }
	 
	 boolean prev = startPageNum == 1 ? false : true;
	 boolean next = endPageNum * pageNum_cnt >= count ? false : true;
	 
	 
	 List<BoardVO> list = boardService.listPage(displayPost, postNum);
	 model.addAttribute("list", list);   
	 model.addAttribute("pageNum", pageNum);
	 
	// 시작 및 끝 번호
	 model.addAttribute("startPageNum", startPageNum);
	 model.addAttribute("endPageNum", endPageNum);

	 // 이전 및 다음 
	 model.addAttribute("prev", prev);
	 model.addAttribute("next", next);
	 
	// 현재 페이지
	 model.addAttribute("select", num);
	 */
		Page page = new Page();
		
		page.setNum(num);
		page.setCount(service.count());  

		List<boardvo> list = null; 
		list = service.listPage(page.getDisplayPost(), page.getPostNum());

		model.addAttribute("list", list);   
		model.addAttribute("pageNum", page.getPageNum());

		model.addAttribute("startPageNum", page.getStartPageNum());
		model.addAttribute("endPageNum", page.getEndPageNum());
		 
		  model.addAttribute("prev", page.getPrev());
		model.addAttribute("next", page.getNext());  

		model.addAttribute("select", num);
		
	}
	
	
}