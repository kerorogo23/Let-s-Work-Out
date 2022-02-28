package com.classtype.model;

import java.util.List;
import java.util.Set;
import com.groupclass.model.GroupClassVO;
import com.grouphour.model.GroupHourVO;


public interface ClassTypeDAO_interface {
	public void insert(ClassTypeVO classTypeVO);
	public void update(ClassTypeVO classTypeVO);
	public void delete(Integer ClassTypeNo);
	public ClassTypeVO findByPrimaryKey(Integer classTypeNo);
	public List<ClassTypeVO> getAll();
	public Set<GroupClassVO> getGroupClassesByClassTypeNo(Integer classTypeNo);
}
