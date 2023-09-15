package _deadlock;

public class DefectiveStudent {

	private Tutor tutor;

	DefectiveStudent(Tutor tutor) {
		this.tutor = tutor;
	}

	public synchronized void startStudy() {
		// study
		System.out.println("Student is studying");
	}

	public synchronized void handInAssignment() {
		tutor.getProgressReport();
		System.out.println("Student handed in assignment");
	}

}
