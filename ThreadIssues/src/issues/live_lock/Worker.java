package issues.live_lock;

public class Worker {

	private String name;
	private boolean active;

	public Worker(String name, boolean active) {
		this.name = name;
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}

	public synchronized void work(SharedResource sharedResource, Worker otherWorker) {
		while (active)
		{
			if(sharedResource.getOwner() != this) {
				try {
					wait(10);
				} catch (InterruptedException e) {

				}
				continue;
			}
			if(otherWorker.isActive()) {
				System.out.println(getName() + " : give the resource to the worker " + otherWorker.getName());
				sharedResource.setOwner(otherWorker);
				continue;
			}

			//in our example code never come to this line
			//they regularly give the lock each other
			//when otherWorker is active, lock gives to otherWorker and return to start of while loop
			//but until come to this line from start of loop, otherWorker gives back lock to us
			//so, code keeps loops between 24-36 in both workers,
			//and it never comes to here we called this problem as LiveLock

			System.out.println(getName() + " : working on the common resource");
			active = false;
			sharedResource.setOwner(otherWorker);
		}
	}

}
