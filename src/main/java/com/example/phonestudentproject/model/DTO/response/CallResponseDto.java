package com.example.phonestudentproject.model.DTO.response;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Дто "Отчет о совершение звонка".
 */
@Getter
@Setter
@RequiredArgsConstructor
public class CallResponseDto {

    @Schema(description = "Длительнсть звонка")
    private String timeCall;
    @Schema(description = "Время начала звонка")
    private LocalDateTime startTime;
    @Schema(description = "Время окончания звонка")
    private LocalDateTime endTime;
    @Schema(description = "Номер с которого совершается звонок")
    private String phoneFrom;
    @Schema(description = "Номер на который совершается звонок")
    private String phoneTo;
    @Schema(description = "Информация о звонка")
    private String notes;
    @Schema(description = "Маркер успешности звонка")
    private boolean isSuccessfulCall;
    @Schema(description = "Стоимость звонка")
    private String spentMoney;
    @Schema(description = "Дто телефона с которого совершается звонок")
    private PhoneDTO phoneDtoFrom;
    @Schema(description = "ДТО телефона на который совершается звонок")
    private PhoneDTO phoneDtoTo;

    private CallResponseDto(Builder builder) {
        this.timeCall = builder.timeCall;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.phoneFrom = builder.phoneFrom;
        this.phoneTo = builder.phoneTo;
        this.notes = builder.notes;
        this.isSuccessfulCall = builder.isSuccessfulCall;
        this.spentMoney = builder.spentMoney;
        this.phoneDtoFrom = builder.phoneDtoFrom;
        this.phoneDtoTo = builder.phoneDtoTo;
    }

    public static class Builder {
        private String timeCall;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private String phoneFrom;
        private String phoneTo;
        private String notes;
        private boolean isSuccessfulCall;
        private String spentMoney;
        private PhoneDTO phoneDtoFrom;
        private PhoneDTO phoneDtoTo;

        public Builder timeCall(String timeCall) {
            this.timeCall = timeCall;
            return this;
        }

        public Builder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder phoneFrom(String phoneFrom) {
            this.phoneFrom = phoneFrom;
            return this;
        }

        public Builder phoneTo(String phoneTo) {
            this.phoneTo = phoneTo;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder isSuccessfulCall(boolean isSuccessfulCall) {
            this.isSuccessfulCall = isSuccessfulCall;
            return this;
        }

        public Builder spentMoney(String spentMoney) {
            this.spentMoney = spentMoney;
            return this;
        }

        public Builder phoneDtoFrom(PhoneDTO phoneDtoFrom) {
            this.phoneDtoFrom = phoneDtoFrom;
            return this;
        }

        public Builder phoneDtoTo(PhoneDTO phoneDtoTo) {
            this.phoneDtoTo = phoneDtoTo;
            return this;
        }

        public CallResponseDto build() {
            return new CallResponseDto(this);
        }
    }

}
