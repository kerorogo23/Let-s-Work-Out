package com.classtype.model;

import java.util.List;
import java.util.Set;
import com.groupclass.model.GroupClassVO;

public class ClassTypeService {
	private ClassTypeDAO_interface dao;
	
	public ClassTypeService(){
		dao = new ClassTypeJDBCDAO();
	}
	
	public ClassTypeVO addClassType(String classTypeName) {
		ClassTypeVO classTypeVO = new ClassTypeVO();
		classTypeVO.setClassTypeName(classTypeName);
		
		dao.insert(classTypeVO);
		return classTypeVO;
	}
	
	public ClassTypeVO updateClassType(Integer classTypeNo, String classTypeName) {
		ClassTypeVO classTypeVO = new ClassTypeVO();
		classTypeVO.setClassTypeNo(classTypeNo);
		classTypeVO.setClassTypeName(classTypeName);
		
		dao.update(classTypeVO);;
		return classTypeVO;
	}
	
	public void deleteClassType(Integer classTypeNo) {
		dao.delete(classTypeNo);
	}

	public ClassTypeVO getOneClassType(Integer classTypeNo) {
		return dao.findByPrimaryKey(classTypeNo);
	}

	public List<ClassTypeVO> getAll() {
		return dao.getAll();
	}
	public Set<GroupClassVO> getGroupClassesByClassTypeNo(Integer classTypeNo) {
		return dao.getGroupClassesByClassTypeNo(classTypeNo);
	}
}
