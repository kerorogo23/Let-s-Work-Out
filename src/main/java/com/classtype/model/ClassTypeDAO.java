package com.classtype.model;

import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.groupclass.model.GroupClassVO;

public class ClassTypeDAO implements ClassTypeDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public static final String INSERT_STMT = 
			"INSERT INTO class_type (c_name) values (?)";
	public static final String GET_ALL_STMT = 
			"SELECT c_type_no, c_name FROM class_type ORDER BY c_type_no";
	public static final String GET_ONE_STMT = 
			"SELECT c_type_no, c_name FROM class_type WHERE c_type_no =?";
	private static final String GET_GroupClasses_ByClassTypeNo_STMT = 
			"SELECT * FROM group_class where classTypeNo = ? order by groupclassNo";
	public static final String DELETE_G = 
			"DELETE FROM class_type WHERE c_type_no = ?";
	public static final String DELETE_CLASSTYPE = 
			"DELETE FROM class_type WHERE c_type_no = ?";
	public static final String UPDATE = 
			"UPDATE class_type set c_name=? WHERE c_type_no=?";
	@Override
	public void insert(ClassTypeVO classTypeVO) {
		
		
	}

	@Override
	public void update(ClassTypeVO classTypeVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer ClassTypeNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClassTypeVO findByPrimaryKey(Integer ClassTypeNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClassTypeVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<GroupClassVO> getGroupClassesByClassTypeNo(Integer classTypeNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
