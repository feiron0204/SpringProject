package board.controller;


import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardDTO;
import board.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value = "boardWriteForm")
	public String boardWriteForm(Model model) {
		model.addAttribute("display", "/board/boardWriteForm.jsp");
		return "/index";
	}
	
	@PostMapping(value = "boardWrite")
	@ResponseBody
	public void boardWrite(@RequestParam Map<String, String> map) {
		boardService.boardWrite(map);
	}
	
	@GetMapping(value = "boardList")
	public String boardList(@RequestParam(required=false, defaultValue = "1")String pg, Model model) {
		model.addAttribute("display", "/board/boardList.jsp");
		model.addAttribute("pg", pg);
		return "/index";
	}
	
	@PostMapping(value = "getBoardList")
	@ResponseBody
	public Map<String,Object> getBoardList(@RequestParam(required=false, defaultValue = "1") String pg){
		return boardService.getBoardList(pg);
	}
	
	@GetMapping(value = "boardView")
	public String boardView(@RequestParam Map<String,String> map,Model model) {
		model.addAttribute("display","/board/boardView.jsp");
		model.addAttribute("pg", map.get("pg"));
		model.addAttribute("seq", map.get("seq"));
		return "/index";
	}
	@PostMapping(value = "getBoardView")
	@ResponseBody
	public Map<String,Object> getBoardView(@RequestParam String seq,@CookieValue(name = "memHit", required = false) Cookie cookie) {
		System.out.println(cookie);
		return boardService.getBoardView(seq,cookie);
	}
	
	@PostMapping(value = "boardModifyForm")
	public String boardModifyForm(@RequestParam Map<String, String> map,Model model) {
		model.addAttribute("display", "/board/boardModifyForm.jsp");
		model.addAttribute("boardDTO", boardService.boardModifyForm(map.get("seq")));
		model.addAttribute("pg", map.get("pg"));
		return "/index";
	}
	
	@PostMapping(value = "boardModify")
	@ResponseBody
	public void boardModify(@ModelAttribute BoardDTO boardDTO) {
		boardService.boardModify(boardDTO);
	}
	
	@PostMapping(value = "boardDelete")
	public ModelAndView boardDelete(@RequestParam String seq) {
		boardService.boardDelete(seq);
		return new ModelAndView("redirect:/board/boardList");
	}
	
	@PostMapping(value = "boardReplyForm")
	public String boardReplyForm(@RequestParam String seq,@RequestParam String pg, Model model) {
		model.addAttribute("pseq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/board/boardReplyForm.jsp");
		return "/index";
	}
	
	@PostMapping(value = "boardReply")
	@ResponseBody
	public void boardReply(@RequestParam Map<String, String> map) {
		boardService.boardReply(map);
	}
	
	@PostMapping(value = "getBoardSearchList")
	@ResponseBody
	public Map<String,Object> getBoardSearchList(@RequestParam Map<String,String> map,@RequestParam(required=false, defaultValue = "1") String pg){
		return boardService.getBoardSearchList(map,pg);
	}
}
