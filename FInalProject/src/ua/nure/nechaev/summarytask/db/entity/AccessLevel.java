package ua.nure.nechaev.summarytask.db.entity;

public enum AccessLevel {
	Dispatcher(2), Administrator(1);

	private int intVal;

	private AccessLevel(int intVal) {
		this.intVal = intVal;
	}

	public int getIntValue() {
		return this.intVal;
	}

}
