import org.junit.jupiter.api.Test;
import study.stepup.Account;
import study.stepup.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {
    @Test
    public void testName() {
        Account account = new Account("Иван");
        assertThrows(IllegalArgumentException.class, ()-> account.setName(""));
        assertEquals(account.getName(), "Иван");
    }

    @Test
    public void testRest() {
        Account account = new Account("Иван");
        account.setRest(Currency.RUB, 10);
        assertEquals(account.getRest().get(Currency.RUB), 10);
        assertThrows(IllegalArgumentException.class, ()-> account.setRest(Currency.USD, -3));
    }
}
