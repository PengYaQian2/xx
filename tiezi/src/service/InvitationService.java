package service;

import java.util.List;
import pojo.Invitation;

public interface InvitationService {
	List<Invitation> query(String title, int currNo, int size);

	int count(String title);

	boolean delete(int id);
}
