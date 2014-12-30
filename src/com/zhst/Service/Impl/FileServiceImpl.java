package com.zhst.Service.Impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhst.Bean.BaseEntity;
import com.zhst.Bean.Item;
import com.zhst.Dao.ItemDao;
import com.zhst.Service.FileService;


@Service("FileService")
public class FileServiceImpl extends BaseServiceImpl implements FileService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    @Qualifier("ItemDao")
	private ItemDao itemdao;
	

	public ItemDao getItemdao() {
		return itemdao;
	}

	public void setItemdao(ItemDao itemdao) {
		this.itemdao = itemdao;
	}

	public void save(Item file) {
		// TODO Auto-generated method stub
		this.getItemdao().save(file);
	}
	
	@Override
	public void upHomework() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downloadHomework() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Item> getAllHomework() {
		// TODO Auto-generated method stub
		
		return itemdao.find("from Item");
	}

}
