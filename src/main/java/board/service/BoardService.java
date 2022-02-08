package board.service;

import java.util.Map;



public interface BoardService {

	public void boardWrite(Map<String, String> map);

	public Map<String,Object> getBoardList(String pg);

	public Map<String,Object> getBoardView(String seq);

}
