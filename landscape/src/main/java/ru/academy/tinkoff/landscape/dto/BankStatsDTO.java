package ru.academy.tinkoff.landscape.dto;

import lombok.*;
import ru.academy.tinkoff.landscape.handyman.PaymentMethod;

import java.util.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
public class BankStatsDTO {
    private String bankName;
    private Date minRegisterTime;
    private Date maxRegisterTime;


    public BankStatsDTO(PaymentMethod bank, Date minRegisterTime, Date maxRegisterTime) {
        this.bankName = bank.name();
        this.minRegisterTime = minRegisterTime;
        this.maxRegisterTime = maxRegisterTime;
    }
}
