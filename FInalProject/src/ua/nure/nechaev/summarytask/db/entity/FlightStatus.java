package ua.nure.nechaev.summarytask.db.entity;

public enum FlightStatus {
	Active(1), Delayed(2), Canceled(3), Pending(4);

	private int id;

	private FlightStatus(int value) {
		this.id = value;
	}

	public int getId() {
		return id;
	}

	public static FlightStatus getStatus(int id) {
		FlightStatus stat = null;
		if (FlightStatus.Active.id == id) {
			stat = FlightStatus.Active;
		} else if (FlightStatus.Delayed.id == id) {
			stat = FlightStatus.Delayed;
		} else if (FlightStatus.Canceled.id == id) {
			stat = FlightStatus.Canceled;
		} else if (FlightStatus.Pending.id == id) {
			stat = FlightStatus.Pending;
		}
		return stat;
	}
}
