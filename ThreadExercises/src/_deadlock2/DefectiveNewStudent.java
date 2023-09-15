package _deadlock2;

public class DefectiveNewStudent {

	private DefectiveNewTutor tutor;

	DefectiveNewStudent(DefectiveNewTutor tutor) {
		this.tutor = tutor;
	}

	public void startStudy() {
		// study
		System.out.println("Student is studying");
	}

	public void handInAssignment() {
		synchronized (tutor) {
			tutor.getProgressReport();
			synchronized (this) {
				System.out.println("Student handed in assignment");
				tutor.notifyAll();
			}
		}
	}

}
