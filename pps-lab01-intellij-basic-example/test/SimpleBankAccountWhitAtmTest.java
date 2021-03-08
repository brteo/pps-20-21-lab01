import lab01.example.model.AccountHolder;
import lab01.example.model.SimpleBankAccountWithAtm;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccountWithAtm implementation
 */
public class SimpleBankAccountWhitAtmTest extends SimpleBankAccountTest {

    @BeforeEach
    void beforeEach() {
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccountWithAtm(accountHolder, 0);
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.getId(), WITHDRAW_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT - WITHDRAW_AMOUNT - SimpleBankAccountWithAtm.WITHDRAW_FEE, bankAccount.getBalance());
    }

    @Test
    void testWithdrawNotAllowed() {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(1, DEPOSIT_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }
}
