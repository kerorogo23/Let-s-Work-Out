package com.members.model;

import java.util.Date;
import java.util.List;

public class MembersService {

	private MembersDAO_interface dao;

	public MembersService() {
		dao = new MembersJDBCDAO();
	}

	public MembersVO addMembers(String memName, String memAcc, String memPsw,
			java.sql.Date memBir, String sex, String memAddr, String memEmail, 
			String memPhone) {

		MembersVO membersVO = new MembersVO();
		
		membersVO.setMemName(memName);
		membersVO.setMemAcc(memAcc);
		membersVO.setMemPsw(memPsw);
		membersVO.setMemBir(memBir);
		membersVO.setSex(sex);
		membersVO.setMemAddr(memAddr);
		membersVO.setMemEmail(memEmail);
		membersVO.setMemPhone(memPhone);
		membersVO.setMemResume("");
		membersVO.setMemRegDate(new java.sql.Date(new Date().getTime()));
		membersVO.setAllAuth(1);
		membersVO.setArtAuth(1);
		membersVO.setComAuth(1);
		dao.insert(membersVO);

		return membersVO;
	}

	public MembersVO updateMembers(Integer memId, String memName, String memAcc,
			String memPsw, java.sql.Date memBir, String sex, String memAddr, String memEmail, 
			String memPhone, String memResume, java.sql.Date memRegDate, 
			Integer allAuth, Integer artAuth, Integer comAuth) {

		MembersVO membersVO = new MembersVO();

		membersVO.setMemId(memId);
		membersVO.setMemName(memName);
		membersVO.setMemAcc(memAcc);
		membersVO.setMemPsw(memPsw);
		membersVO.setMemBir(memBir);
		membersVO.setSex(sex);
		membersVO.setMemAddr(memAddr);
		membersVO.setMemEmail(memEmail);
		membersVO.setMemPhone(memPhone);
		membersVO.setMemResume(memResume);
		membersVO.setMemRegDate(memRegDate);
		membersVO.setAllAuth(allAuth);
		membersVO.setArtAuth(artAuth);
		membersVO.setComAuth(comAuth);
		dao.update(membersVO);

		return membersVO;
	}

	public void deleteMembers(Integer memId) {
		dao.delete(memId);
	}

	public MembersVO getOneMembers(Integer memId) {
		return dao.findByPrimaryKey(memId);
	}
	
	public MembersVO getOneMembersByMemAcc(String memAcc) {
		return dao.findByPrimaryKeyByMemAcc(memAcc);
	}

	public List<MembersVO> getAll() {
		return dao.getAll();
	}
}
