package service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mapper.DetailMapper;
import pojo.Detail;
import service.DetailService;

@Transactional
@Service
public class DetailServiceImpl implements DetailService {
	@Autowired
	private DetailMapper detailMapper;

	@Override
	public List<Detail> querys(int invid, int currNo, int size) {
		try {
			List<Detail> list = detailMapper.querys(invid, currNo, size);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int count(int invid) {
		try {
			int count = detailMapper.count(invid);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean add(Detail detail) {
		try {
			Date dates = new Date();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String date = format.format(dates);
			
			detail.setCreatedate(date);		
			
			int count = detailMapper.add(detail);
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
