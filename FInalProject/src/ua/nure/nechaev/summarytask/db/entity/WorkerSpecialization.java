package ua.nure.nechaev.summarytask.db.entity;

public enum WorkerSpecialization {
	Pilot(1), Navigator(2), Operator(3), Stewardess(4);

	private int id;

	private WorkerSpecialization(int intVal) {
		this.id = intVal;
	}

	public int getIntVal() {
		return this.id;
	}

	public static WorkerSpecialization getSpec(int specCode) {
		WorkerSpecialization spc = null;
		if (specCode == WorkerSpecialization.Navigator.getIntVal()) {
			spc = WorkerSpecialization.Navigator;
		} else if (specCode == WorkerSpecialization.Operator.getIntVal()) {
			spc = WorkerSpecialization.Operator;
		} else if (specCode == WorkerSpecialization.Pilot.getIntVal()) {
			spc = WorkerSpecialization.Pilot;
		} else if (specCode == WorkerSpecialization.Stewardess.getIntVal()) {
			spc = WorkerSpecialization.Stewardess;
		}
		return spc;
	}
}
