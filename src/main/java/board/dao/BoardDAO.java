package board.dao;

import java.util.List;
import java.util.Map;

import board.bean.BoardDTO;

public interface BoardDAO {

	public void boardWrite(Map<String, String> map);

	public List<BoardDTO> getBoardList(Map<String, Integer> map);

	public int getTotalA();

	public BoardDTO getBoardView(String seq);

	public BoardDTO boardModifyForm(String seq);

	public void boardModify(BoardDTO boardDTO);

	public void boardDelete(BoardDTO boardDTO);

}
