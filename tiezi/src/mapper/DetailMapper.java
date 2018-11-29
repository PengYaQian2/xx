package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Detail;

public interface DetailMapper {
	List<Detail> querys(@Param("invid") int invid, @Param("currNo") int currNo, @Param("size") int size);

	int count(@Param("invid") int invid);

	int add(Detail detail);

}
