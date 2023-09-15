package _deadlock;

public class Student {
	private Tutor tutor;
	private boolean isStudying;
	Student(Tutor tutor) {
		this.tutor = tutor;
		isStudying = false;
	}

	public boolean isStudying() {
		return isStudying;
	}

	public void setStudying(boolean studying) {
		isStudying = studying;
	}

	public synchronized void startStudy() {
		// study
		isStudying = true;
		System.out.println("Student is studying");

	}

	public synchronized void handInAssignment() {
		while(!isStudying)
		{
			try {
				wait(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

		tutor.getProgressReport();
		System.out.println("Student handed in assignment");
	}
}
