package com.product_category.model;

import java.util.*;

import com.product.model.ProductVO;



public interface ProductCategoryDAO_interface {
	public void insert(ProductCategoryVO productcategoryVO);
    public void update(ProductCategoryVO productcategoryVO);
    public void delete(Integer productcategory_no);
    public ProductCategoryVO findByPrimaryKey(Integer p_category_no);
    public List<ProductCategoryVO> getAll();
    public Set<ProductVO> getProductsByPC(Integer p_category_no);
}


