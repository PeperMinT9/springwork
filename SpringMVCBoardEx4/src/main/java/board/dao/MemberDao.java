package board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import board.dto.MemberDto;

@Repository
public class MemberDao {
	@Autowired
	private SqlSession session;
	
	String nameSpace = "board.dao.MemberDao.";
	
	public int getTotalCount() {
		return session.selectOne(nameSpace + "totalCountOfMember");
	}
	
	public void insertMember(MemberDto dto) {
		session.insert(nameSpace + "insertMember", dto);
	}
	
	public List<MemberDto> getAllMembers() {
		return session.selectList(nameSpace + "selectAllOfMember");
	}
	
	public void deleteMember(int num) {
		session.delete(nameSpace + "deleteMember", num);
	}
	
	public int isEqualEmail(String email) {
		return session.selectOne(nameSpace + "isEqualEmail", email);
	}
	
	public int isEqualPassEmail(String email, String pass) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("pass", pass);
		
		return session.selectOne(nameSpace + "isEqualPassEmail", map);
	}
	
	public MemberDto selectOneOfEmail(String email) {
		return session.selectOne(nameSpace + "selectOneOfEmail", email);
	}
	
	public MemberDto selectOneOfNum(int num) {
		return session.selectOne(nameSpace + "selectOneOfNum", num);
	}
	
	public void updateOfMember(MemberDto dto) {
		session.update(nameSpace + "updateMember", dto);
	}
}
