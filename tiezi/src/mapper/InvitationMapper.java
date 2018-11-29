package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Invitation;

public interface InvitationMapper {
	List<Invitation> query(@Param("title") String title, @Param("currNo") int currNo, @Param("size") int size);

	int count(@Param("title") String title);

	int delete(@Param("id") int id);
}
