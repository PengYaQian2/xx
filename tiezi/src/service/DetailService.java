package service;

import java.util.List;

import pojo.Detail;

public interface DetailService {
	List<Detail> querys(int invid, int currNo, int size);

	int count(int invid);

	boolean add(Detail detail);
}
