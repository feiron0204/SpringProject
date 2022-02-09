package board.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
	@Autowired
	HttpServletResponse resp;
	
	
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
		
		if(httpSession.getAttribute("memId")!=null) {
			Cookie cookie = new Cookie("memHit", "0");
			cookie.setMaxAge(30*60);
			resp.addCookie(cookie);
		}
		
		Map<String,Object> temp=new HashMap<String, Object>();
		temp.put("list", boardDAO.getBoardList(map));
		temp.put("memId", httpSession.getAttribute("memId"));
		temp.put("boardPaging", boardPaging.getPagingHTML().toString());
		
		
		return temp;
	}
	@Override
	public Map<String,Object> getBoardView(String seq,Cookie cookie) {
		if(cookie!=null) {
			boardDAO.boardHit(seq);
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
		}
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
		boardDAO.boardDelete(seq);
	}
	@Override
	public void boardReply(Map<String, String> map) {
		BoardDTO boardDTO=(BoardDTO) this.getBoardView(map.get("pseq"),null).get("boardDTO");
		
		String id = (String) httpSession.getAttribute("memId");
		String name = (String) httpSession.getAttribute("memName");
		String email = (String) httpSession.getAttribute("memEmail");
		
		map.put("id", id);
		map.put("name", name);
		map.put("email", email);
		map.put("ref", boardDTO.getRef()+"");
		map.put("lev", boardDTO.getLev()+"");
		map.put("step", boardDTO.getStep()+"");
		
		boardDAO.boardReply(map);
	}
}
