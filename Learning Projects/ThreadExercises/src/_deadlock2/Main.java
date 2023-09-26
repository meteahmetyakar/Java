package _deadlock2;

public class Main {

	public static void main(String[] args) {
		final NewTutor tutor = new NewTutor();
		final NewStudent student = new NewStudent(tutor);
		tutor.setStudent(student);

		Thread tutorThread = new Thread(new Runnable() {
			@Override
			public void run() {
				tutor.studyTime();
			}
		});

		Thread studentThread = new Thread(new Runnable() {
			@Override
			public void run() {
				student.handInAssignment();
			}
		});

		tutorThread.start();
		studentThread.start();
	}

}
