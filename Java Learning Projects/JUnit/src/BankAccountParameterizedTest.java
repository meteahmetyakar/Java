import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

public class BankAccountParameterizedTest {

	private BankAccount account;

	@BeforeEach
	public void setup()
	{
		account = new BankAccount("Mete Ahmet", "Yakar", 1000.00, BankAccount.CHECKING);
		System.out.println("Running a test...");
	}

	@ParameterizedTest
	@CsvSource({
			"100.00, true, 1100.00",
			"200.00, true, 1200.00",
			"325.14, true, 1325.14",
			"489.33, true, 1489.33",
			"1000.00, true, 2000.00"
	})
	public void deposit(double amount, boolean branch, double expected)
	{
		account.deposit(amount, branch);
		assertEquals(expected, account.getBalance(), .01);
	}

}
