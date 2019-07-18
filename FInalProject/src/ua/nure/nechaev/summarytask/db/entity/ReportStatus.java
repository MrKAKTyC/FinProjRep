package ua.nure.nechaev.summarytask.db.entity;

public enum ReportStatus {
	completed(1), rejected(2), pending(3);

	private int intVal;

	ReportStatus(int val) {
		this.intVal = val;
	}
	
	public int getIntVal() {
		return intVal;
	}

	public static ReportStatus getStatus(int statusId) {
		ReportStatus status = null;
		if (completed.intVal == statusId) {
			status = completed;
		} else if (rejected.intVal == statusId) {
			status = rejected;
		} else if (pending.intVal == statusId) {
			status = pending;
		}
		return status;
	}
}
