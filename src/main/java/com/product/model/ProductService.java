package com.product.model;

import java.util.List;





public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductJDBCDAO();
	}

//	public ProductVO addProduct(Integer p_category_no, String p_name,Integer p_price, String p_detail,Integer p_status,
//			java.sql.Date p_upload_time) {
//
//		ProductVO productVO = new ProductVO();
//
//		productVO.setP_category_no(p_category_no);
//		productVO.setP_name(p_name);
//		productVO.setP_price(p_price);
//		productVO.setP_detail(p_detail);
//		productVO.setP_status(p_status);
//		productVO.setP_upload_time(p_upload_time);
//		dao.insert(productVO);
//
//		return productVO;
//	}
public Integer addProduct(ProductVO productVO) {
		
		Integer newProduct_no = dao.insert(productVO);
		
		return newProduct_no;
	}

	public ProductVO updateProduct(Integer product_no,Integer p_category_no, String p_name,Integer p_price, String p_detail,Integer p_status,
			java.sql.Date p_upload_time) {

		ProductVO productVO = new ProductVO();
		
		productVO.setProduct_no(product_no);
		productVO.setP_category_no(p_category_no);
		productVO.setP_name(p_name);
		productVO.setP_price(p_price);
		productVO.setP_detail(p_detail);
		productVO.setP_status(p_status);
		productVO.setP_upload_time(p_upload_time);
		dao.update(productVO);

		return productVO;
	}

	public void deleteProduct(Integer product_no) {
		dao.delete(product_no);
	}
	public ProductVO getOneProduct(Integer product_no) {
		return dao.findByPrimaryKey(product_no);
	}
	public List<ProductVO> searchProduct(String keyword){

		return dao.findByProductKeyword(keyword);
	}
	public List<ProductVO> getProductFromPC(List<Integer> product_no){

		return dao.findByProduct_no(product_no);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}

	public List<ProductVO> frontEndSearchProduct(String keyword){
		return dao.frontEndFindByProductKeyword(keyword);
	}
	
	public List<ProductVO> frontEndgetItemFromPC(List<Integer> product_no){
		return dao.frontEndFindByProduct_no(product_no);
	}
	public List<ProductVO> frontEndgetAll(){
		return dao.frontEndGetAll();
	}
}
