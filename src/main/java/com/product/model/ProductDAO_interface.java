package com.product.model;

import java.util.*;






public interface ProductDAO_interface{
	public Integer insert(ProductVO productVO);
    public void update(ProductVO productVO);
    public void delete(Integer product_no);
    public ProductVO findByPrimaryKey(Integer product_no);
    public List<ProductVO> getAll();
    public List<ProductVO> findByProductKeyword(String keyword);
	public List<ProductVO> findByProduct_no(List<Integer> product_no);
	public List<ProductVO> frontEndFindByProductKeyword(String keyword);
	public List<ProductVO> frontEndFindByProduct_no(List<Integer> product_no);
	public List<ProductVO> frontEndGetAll();
}

