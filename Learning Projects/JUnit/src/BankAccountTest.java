import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
	private BankAccount account;
	private static int count;

	@BeforeAll
	public static void beforeClass() {
		count = 0;
		System.out.println("This executes before any test cases. Count = " + count++);
	}

	@BeforeEach
	public void setup()
	{
		account = new BankAccount("Mete Ahmet", "Yakar", 1000.00, BankAccount.CHECKING);
		System.out.println("Running a test...");

	}

	@org.junit.jupiter.api.Test
	void deposit() {
		double balance = account.deposit(200.00, true);
		assertEquals(1200.00, account.getBalance(), 0);

	}


	@org.junit.jupiter.api.Test
	public void withdraw_branch() throws Exception {
		double balance = account.withdraw(600.00, true);
		assertEquals(400.00, balance, 0);
	}

	@org.junit.jupiter.api.Test
	public void withdraw_notBranch() throws Exception {

		assertThrows(IllegalArgumentException.class, () -> {
			double balance = account.withdraw(600.00, false);
		});

	}

	@org.junit.jupiter.api.Test
	void getBalance_deposit() {
		account.deposit(200.00, true);
		assertEquals(1200.00, account.getBalance(),0);
	}

	@org.junit.jupiter.api.Test
	void getBalance_withdraw() {
		account.withdraw(200.00, true);
		assertEquals(800, account.getBalance(),0);
	}

	@org.junit.jupiter.api.Test
	public void isChecking_true() {
		assertTrue(account.isChecking(),"The account is NOT a checking account");
	}

	@AfterAll
	public static void afterClass()
	{
		System.out.println("This executes after any test cases. Count = " + count++);
	}

	@AfterEach
	public void teardown()
	{
		System.out.println("Count = " + count++);
	}

}