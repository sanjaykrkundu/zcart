package com.zycus.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zycus.dao.ProductDao;
import com.zycus.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public boolean addProduct(Product product) {
		Integer productId = (Integer) hibernateTemplate.save(product);
		if(productId!=null)
			return true;
		return false;
	}

	public boolean updateProduct(Product product) {
		try {
			hibernateTemplate.update(product);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean removeProduct(Product product) {
		try {
			hibernateTemplate.delete(product);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public Product getProduct(int productId) {
		return hibernateTemplate.get(Product.class, productId);
	}

	public List<Product> getProductList() {
		return hibernateTemplate.loadAll(Product.class);
	}

	public List<Product> getProductListByCategory(String category) {
		return (List<Product>) hibernateTemplate.find("from Product where upper(productCategory)=?",new Object[]{category.toUpperCase()});
	}

	@Override
	public List<Product> serachProduct(String name) {
		return (List<Product>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Product.class).add(Restrictions.ilike("productName", name, MatchMode.START)));
	}

}
