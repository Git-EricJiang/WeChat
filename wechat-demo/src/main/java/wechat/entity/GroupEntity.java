package wechat.entity;

public class GroupEntity {

	private GroupInfoEntity group;

	public GroupInfoEntity getGroup() {
		return group;
	}

	public void setGroup(GroupInfoEntity group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "GroupEntity [group=" + group + "]";
	}

	
}
