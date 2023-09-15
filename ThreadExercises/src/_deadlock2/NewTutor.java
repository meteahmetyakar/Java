package _deadlock2;

public class NewTutor {
	private NewStudent student;

	public void setStudent(NewStudent student) {
		this.student = student;
	}

	public void studyTime() {

		System.out.println("Tutor has arrived");
		synchronized (student) {
			student.startStudy();
			System.out.println("Tutor is studying with student");
		}
	}

	public void getProgressReport() {
		// get progress report
		System.out.println("Tutor gave progress report");
	}

}
