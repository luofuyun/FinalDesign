package com.zhst.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhst.Bean.Submit;
import com.zhst.Dao.SubmitDao;
import com.zhst.Service.SubmitService;

@Service("SubmitService")
public class SubmitServiceImpl extends BaseServiceImpl implements SubmitService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    @Qualifier("SubmitDao")
	private SubmitDao submitdao;
	
}
