package com.zhst.Service;

import java.util.List;

import com.zhst.Bean.Item;



public interface FileService extends BaseService{
	public void upHomework();
	public void downloadHomework();
	public List<Item> getAllHomework();
}
