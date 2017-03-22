package wechat.entity.util;

import wechat.entity.GroupEntity;
import wechat.entity.GroupInfoEntity;

public class WechatEntityUtils {

	public static GroupEntity createGroupByName(String groupName) {
		
		GroupInfoEntity info = new GroupInfoEntity(groupName);
		GroupEntity ge = new GroupEntity();
		ge.setGroup(info);
		return ge;
	}

	public static GroupEntity deleteGroupById(int groupId) {
		
		GroupInfoEntity info = new GroupInfoEntity(groupId);
		GroupEntity ge = new GroupEntity();
		ge.setGroup(info);
		return ge;
	}
}
