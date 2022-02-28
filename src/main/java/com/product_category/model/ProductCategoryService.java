package com.product_category.model;

import java.util.List;
import java.util.Set;


import com.product.model.ProductVO;


public class ProductCategoryService {
	private ProductCategoryDAO_interface dao;

	public ProductCategoryService() {
		dao = new ProductCategoryJDBCDAO();
	}

	public List<ProductCategoryVO> getAll() {
		return dao.getAll();
	}

	public ProductCategoryVO getOnePC(Integer p_category_no) {
		return dao.findByPrimaryKey(p_category_no);
	}

	public Set<ProductVO> getProductsByPC(Integer p_category_no) {
		return dao.getProductsByPC(p_category_no);
	}

	public void deletePC(Integer p_category_no) {
		dao.delete(p_category_no);
	}
	public void getOne_For_Update_PC(Integer p_category_no) {
		dao.delete(p_category_no);
	}

}

