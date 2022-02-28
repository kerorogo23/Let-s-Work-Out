package com.product_photo.model;


import java.util.List;





public class ProductPhotoService {
	private ProductPhotoDAO_interface dao;
	public ProductPhotoService() {
		dao = new ProductPhotoJDBCDAO();
	}

	public ProductPhotoVO addProductPhoto(Integer product_no,byte[] p_photo){

		ProductPhotoVO productphotoVO = new ProductPhotoVO();

		productphotoVO.setProduct_no(product_no);
		
		productphotoVO.setP_photo(p_photo);
	
		
		dao.insert(productphotoVO);
	

		return productphotoVO;
	}

	public ProductPhotoVO updateProducphoto(Integer p_photo_no, Integer product_no,byte[] p_photo) {

		ProductPhotoVO productphotoVO = new ProductPhotoVO();
		
		productphotoVO.setP_photo_no(p_photo_no);
		
		
		productphotoVO.setProduct_no(product_no);
		productphotoVO.setP_photo(p_photo);
		
		dao.update(productphotoVO);

		return productphotoVO;
	}

	public void deleteProductPhoto(Integer productphoto) {
		dao.delete(productphoto);
	}

	public ProductPhotoVO getOneProductPhoto(Integer product_photo) {
		return dao.findByPrimaryKey(product_photo);
	}

	public List<ProductPhotoVO> getAll() {
		return dao.getAll();
	}


}
