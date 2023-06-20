package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.domain.BoardVO;
import com.board.service.BoardService;

@Controller
@RequestMapping(value = "/board/")
public class BoardController {

	@Inject
	BoardService service;
	
// 게시물 리스트 조회	
 @RequestMapping(value = "/list", method = RequestMethod.GET)
 public void getList(Model model) throws Exception {
  
	 List<BoardVO> list = service.list();
	 
	 model.addAttribute("list", list);
   
 }
 
 // 게시물 작성
 @RequestMapping(value = "/write", method = RequestMethod.GET)
 public void getWrite() throws Exception{
	 
 }
 
 // 게시물 작성
 @RequestMapping(value = "/write", method = RequestMethod.POST)
 public String postWrite(BoardVO vo)throws Exception{
	 service.write(vo);
	
	 // 모든 작업을 마치고 다시 /board/list, 즉 게시물 목록 화면으로 되돌아가겠다.
	 return "redirect:/board/list";
 }
 
 // 게시물 상세조회
 @RequestMapping(value = "/view", method = RequestMethod.GET)
 public void getView() throws Exception{
	 
 }
 
 
 
}