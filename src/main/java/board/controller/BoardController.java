package board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import board.bean.BoardDTO;
import board.service.BoardService;
import lombok.Builder.Default;

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
}
