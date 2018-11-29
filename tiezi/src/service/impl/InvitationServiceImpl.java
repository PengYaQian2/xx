package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mapper.InvitationMapper;
import pojo.Invitation;
import service.InvitationService;

@Transactional
@Service
public class InvitationServiceImpl implements InvitationService {
	@Autowired
	private InvitationMapper invitationMapper;

	@Override
	public List<Invitation> query(String title, int currNo, int size) {
		try {
			List<Invitation> list = invitationMapper.query(title, currNo, size);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int count(String title) {
		try {
			int count = invitationMapper.count(title);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean delete(int id) {
		try {
			int count = invitationMapper.delete(id);
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
