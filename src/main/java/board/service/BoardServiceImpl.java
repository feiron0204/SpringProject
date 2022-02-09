package board.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO boardDAO;
	@Autowired
	HttpSession httpSession;
	@Autowired
	BoardPaging boardPaging;
	
	@Override
	public void boardWrite(Map<String, String> map) {
		map.put("id",(String) httpSession.getAttribute("memId"));
		map.put("name",(String) httpSession.getAttribute("memName"));
		map.put("email",(String) httpSession.getAttribute("memEmail"));
		boardDAO.boardWrite(map);
	}
	@Override
	public Map<String,Object> getBoardList(String pg) {
		Map<String,Integer> map=new HashMap<String, Integer>();
		map.put("endNum", Integer.parseInt(pg)*5);
		map.put("startNum", Integer.parseInt(pg)*5-4);
		
		//페이징
		int totalA=boardDAO.getTotalA();//총글수
		
		boardPaging.setCurrentPage(Integer.parseInt(pg));
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		
		boardPaging.makePagingHTML();
		
		Map<String,Object> temp=new HashMap<String, Object>();
		temp.put("list", boardDAO.getBoardList(map));
		temp.put("memId", httpSession.getAttribute("memId"));
		temp.put("boardPaging", boardPaging.getPagingHTML().toString());
		return temp;
	}
	@Override
	public Map<String,Object> getBoardView(String seq) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memId", httpSession.getAttribute("memId"));
		map.put("boardDTO", boardDAO.getBoardView(seq));
		return map;
	}
	@Override
	public BoardDTO boardModifyForm(String seq) {
		return boardDAO.boardModifyForm(seq);
	}
	@Override
	public void boardModify(BoardDTO boardDTO) {
		boardDAO.boardModify(boardDTO);
	}
	@Override
	public void boardDelete(String seq) {
		boardDAO.boardDelete(boardDAO.getBoardView(seq));
	}
}
