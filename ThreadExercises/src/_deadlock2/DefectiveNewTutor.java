package _deadlock2;

public class DefectiveNewTutor {

	private DefectiveNewStudent student;

	public void setStudent(DefectiveNewStudent student) {
		this.student = student;
	}

	public void studyTime() {

		synchronized (this) {
			System.out.println("Tutor has arrived");
			synchronized (student) {
				try {
					// wait for student to arrive
					this.wait();
				} catch (InterruptedException e) {

				}
				student.startStudy();
				System.out.println("Tutor is studying with student");
			}
		}
	}

	public void getProgressReport() {
		// get progress report
		System.out.println("Tutor gave progress report");
	}

}
