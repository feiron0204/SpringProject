package member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;

@Repository
@Transactional
public class MemberDAOmybatis implements MemberDAO{
	@Autowired
	private SqlSession sqlSession;

	public MemberDTO login(Map<String, String> map) {
		return sqlSession.selectOne("memberSQL.login", map);
		
	}

	@Override
	public MemberDTO checkId(String id) {
		return sqlSession.selectOne("memberSQL.checkId",id);
	}

	@Override
	public void write(MemberDTO memberDTO) {
		sqlSession.insert("memberSQL.write", memberDTO);
	}

	@Override
	public MemberDTO modifyForm(String id) {
		return sqlSession.selectOne("memberSQL.modifyForm", id);
	}

	@Override
	public void modify(MemberDTO memberDTO) {
		sqlSession.update("memberSQL.modify", memberDTO);
	}

	@Override
	public void delete(String id) {
		sqlSession.delete("memberSQL.delete", id);
	}

}
