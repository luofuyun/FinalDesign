package com.zhst.Dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhst.Bean.Announce;
import com.zhst.Bean.Info;
import com.zhst.Dao.AnnounceDao;

@Repository("announceDao")
public class AnnounceDaoImpl extends BaseDaoImpl implements AnnounceDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Info getInfoByInfoId(String infoId) {
		List<Info> infos = (List<Info>) this.findByProperty(Info.class, "infoId",
				infoId);
		if (infos.size() == 0) {
			return null;
		} else {
			return infos.get(0);
		}
	}
	
	@Override
	public boolean delInfo(Info info) {
		this.saveOrupdate(info);
		return true;
	}
	
}
