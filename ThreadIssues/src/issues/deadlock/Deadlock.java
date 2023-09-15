package issues.deadlock;

public class Deadlock {
	public static void main(String[] args) {

		PolitePerson jane = new PolitePerson("jane");
		PolitePerson john = new PolitePerson("john");

		new Thread(new Runnable() {
			@Override
			public void run() {
				jane.sayHello(john);
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				john.sayHello(jane);
			}
		}).start();

	}

	static class PolitePerson {
		private final String name;

		public PolitePerson(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public synchronized void sayHello(PolitePerson person)
		{
			System.out.format("%s: %s" + " has said hello to me!%n",this.name, person.getName());
			person.sayHelloBack(this);
		}

		public synchronized void  sayHelloBack(PolitePerson person)
		{
			System.out.format("%s: %s" + " has said hello back to me!%n",this.name, person.getName());
		}



	}
}
