package board.service;

import java.util.Map;

import javax.servlet.http.Cookie;

import board.bean.BoardDTO;



public interface BoardService {

	public void boardWrite(Map<String, String> map);

	public Map<String,Object> getBoardList(String pg);

	public Map<String,Object> getBoardView(String seq, Cookie cookie);

	public BoardDTO boardModifyForm(String seq);

	public void boardModify(BoardDTO boardDTO);

	public void boardDelete(String seq);

	public void boardReply(Map<String, String> map);

}
