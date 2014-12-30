package com.zhst.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhst.Bean.Announce;
import com.zhst.Bean.Info;
import com.zhst.Bean.User;
import com.zhst.Dao.AnnounceDao;
import com.zhst.Service.AnnounceService;

@SuppressWarnings("serial")
@Service("announceService")
public class AnnounceServiceImpl extends BaseServiceImpl implements AnnounceService{

	@Autowired
	private AnnounceDao announceDao;

	@Override
	public List<Info> getInfoItem(int start, int pagesize, String status,
			String username,User creator) {
		if((status == null) || ("0".equals(status))){
			return announceDao.find( "from Info a where a.creator.userId = '"+creator.getUserId()+"' and a.valid = '1'", start, pagesize);
		    }else{
		    return announceDao.find( "from Info a where a.creator.userId = '"+creator.getUserId()+"' and a.valid = '1' and a.status = "+status+"", start, pagesize);
		 }
	}

	@Override
	public int getInfoCount(User creator) {
		return announceDao.getTotalCount(" from Info t where t.creator.userId = '"+creator.getUserId()+"' and t.valid = '1'");
	}

	@Override
	public boolean delInfo(String infoId) {
		Info info = this.getInfoByInfoId(infoId);
		info.setValid(0);
		return announceDao.delInfo(info);
	}

	@Override
	public Info getInfoByInfoId(String infoId) {
		return announceDao.getInfoByInfoId(infoId);
	}
}
