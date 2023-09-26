package _deadlock;

public class Main {
	public static void main(String[] args) {
		Tutor tutor = new Tutor();
		Student student = new Student(tutor);

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
