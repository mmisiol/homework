import com.szalkowm.homework.application.loan.LoanFetcher;
import com.szalkowm.homework.application.loan.LoanRepository;
import com.szalkowm.homework.application.loan.extension.FixedTermExtender;
import com.szalkowm.homework.domain.Loan;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class FixedTermExtenderTest {

    private final int extensionTerm = 10;
    private final int id = 1;

    private final LoanFetcher mockFetcher = mock(LoanFetcher.class);
    private final LoanRepository mockRepository = mock(LoanRepository.class);

    private final FixedTermExtender extender = new FixedTermExtender(mockFetcher, mockRepository, extensionTerm);

    @Test
    public void shouldExtendByTerm() {
        when(this.mockFetcher.get(id)).thenReturn(withDueDate(LocalDate.now()));
        extender.extend(id);
        verify(mockRepository).update(eq(withDueDate(LocalDate.now().plusDays(extensionTerm))));
    }

    private Loan withDueDate(LocalDate dueDate) {
        Loan loan = new Loan();
        loan.setId(id);
        loan.setDueDate(dueDate);
        loan.setCost(new BigDecimal("100"));
        loan.setAmount(new BigDecimal("1000"));
        return loan;
    }
}
