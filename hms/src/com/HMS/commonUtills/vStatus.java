package com.HMS.commonUtills;

public enum vStatus {
	OPEN(1, "OPEN"), CLOSE(2, "CLOSE");
	
	private int id;
	private String name;
	
	vStatus(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public static vStatus fromId(Integer id) {
        if (id == null) {
            return null;
        }
        for (vStatus status : values()) {
            if (id.equals(status.getId())) {
                return status;
            }
        }
        return null;
	}
}
