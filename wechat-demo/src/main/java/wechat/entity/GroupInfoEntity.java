package wechat.entity;

public class GroupInfoEntity {

	public GroupInfoEntity() {
	}

	public GroupInfoEntity(String name) {
		this.name = name;
	}

	public GroupInfoEntity(int id) {
		this.id = id;
	}

	private String name;// 分组名称
	
	private int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GroupInfoEntity [name=" + name + ", id=" + id + "]";
	}

}
