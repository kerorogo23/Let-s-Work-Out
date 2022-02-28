package com.product_photo.model;

import java.util.*;



public interface ProductPhotoDAO_interface {
	public void insert(ProductPhotoVO productphotoVO);
    public void update(ProductPhotoVO productphotoVO);
    public void delete(Integer p_photo_no);
    public ProductPhotoVO findByPrimaryKey(Integer p_photo_no);
    public List<ProductPhotoVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//    public List<ProductCategoryVO> getAll(Map<String, String[]> map); 

}
