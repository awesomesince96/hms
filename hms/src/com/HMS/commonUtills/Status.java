package com.HMS.commonUtills;

public enum Status {
	ACTIVE(1, "Active"), INACTIVE(2, "Inactive"), PENDING(3, "Pending");

	private int id;
	private String name;

	Status(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	//user at contryMasterload
	public static Status fromId(Integer id) {
        if (id == null) {
            return null;
        }
        for (Status status : values()) {
            if (id.equals(status.getId())) {
                return status;
            }
        }
        return null;
    }



}
