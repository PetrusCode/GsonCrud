package com.netmind.model;

public enum EnumStudent {
	ADD_STUDENT(1), CALCULATE_STUDENT_MAX_AGE(2), CALCULATE_AVERAGE_AGE(3), EXIT(4);

	private int value = 1;

	private EnumStudent(int value) {
		this.value = value;
	}

	public static EnumStudent desdeValue(int value) {
		for (EnumStudent my : EnumStudent.values()) {
			if (my.value == value) {
				return my;
			}

		}

		return null;
	}

	public int value() {
		return value;
	}
}
