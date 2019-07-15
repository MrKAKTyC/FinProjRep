package ua.nure.nechaev.summarytask.db.bean;

import ua.nure.nechaev.summarytask.db.dao.WorkerSpecializationDAO;
import ua.nure.nechaev.summarytask.db.entity.Worker;
import ua.nure.nechaev.summarytask.db.entity.WorkerSpecialization;

public class WorkerBean {
	private int workerId;
	private String workerName;
	private WorkerSpecialization workerSpecialization;
	
	public static WorkerBean getInstance(Worker worker) {
		WorkerBean workerBean = new WorkerBean();
		workerBean.workerId = worker.getId();
		workerBean.workerName = worker.getName();
		WorkerSpecializationDAO specDAO = new WorkerSpecializationDAO();
		workerBean.workerSpecialization = specDAO.get(worker.getSpec());
		return workerBean;
	}

	public int getWorkerId() {
		return workerId;
	}

	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public WorkerSpecialization getWorkerSpecialization() {
		return workerSpecialization;
	}

	public void setWorkerSpecialization(WorkerSpecialization workerSpecialization) {
		this.workerSpecialization = workerSpecialization;
	}
	
	
}
