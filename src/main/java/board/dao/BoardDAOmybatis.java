package board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import board.bean.BoardDTO;

@Repository
public class BoardDAOmybatis implements BoardDAO {
	@Autowired
	SqlSession sqlSession;
	@Override
	public void boardWrite(Map<String, String> map) {
		sqlSession.insert("boardSQL.boardWrite", map);
	}
	@Override
	public List<BoardDTO> getBoardList(Map<String, Integer> map) {
		return sqlSession.selectList("boardSQL.getBoardList", map);
	}
	@Override
	public int getTotalA() {
		return sqlSession.selectOne("boardSQL.getTotalA");
	}
	@Override
	public BoardDTO getBoardView(String seq) {
		return sqlSession.selectOne("boardSQL.getBoardView", seq);
	}
	@Override
	public BoardDTO boardModifyForm(String seq) {
		return sqlSession.selectOne("boardSQL.boardModifyForm",seq);
	}
	@Override
	public void boardModify(BoardDTO boardDTO) {
		sqlSession.update("boardSQL.boardModify", boardDTO);
	}
	@Override
	public void boardDelete(String seq) {
		sqlSession.delete("boardSQL.boardDelete", Integer.parseInt(seq));
	}
	@Override
	public void boardReply(Map<String, String> map) {
		sqlSession.insert("boardSQL.boardReply",map);
	}
	@Override
	public void boardHit(String seq) {
		sqlSession.update("boardSQL.boardHit", Integer.parseInt(seq));
	}
}
