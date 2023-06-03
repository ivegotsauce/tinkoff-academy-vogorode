package ru.academy.tinkoff.handyman.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.academy.tinkoff.handyman.dto.AccountDTO;
import ru.academy.tinkoff.handyman.entity.Account;
import ru.academy.tinkoff.handyman.entity.PaymentMethod;
import ru.academy.tinkoff.handyman.entity.User;
import ru.academy.tinkoff.handyman.repository.AccountRepository;
import ru.academy.tinkoff.handyman.repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class AccountService {
    private AccountRepository accountRepository;
    private UserRepository userRepository;

    public Account createAccount(Long userId, AccountDTO dto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id " + userId)
        );
        Account account = new Account();
        dtoToAccount(dto, account);
        account.setUser(user);
        return accountRepository.save(account);
    }

    public Account updateAccount(Long id, AccountDTO dto) {
        Account account = findById(id);
        dtoToAccount(dto, account);
        return accountRepository.save(account);
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No account with id " + id)
        );
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    private void dtoToAccount(AccountDTO dto, Account account) {
        account.setCardNumber(dto.cardNumber());
        try {
            account.setPaymentMethod(PaymentMethod.valueOf(dto.paymentMethod()));
        } catch (IllegalArgumentException ignored) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment method provided");
        }
    }
}
