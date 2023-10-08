package com.example.laboratoryworks.SalaryPayments;

import java.time.LocalDate;

public class PaymentEntity {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String positionName;
    private Double profit_tax;
    private Double prof_tax;
    private Double retirement_tax;
    private LocalDate date;
    private Double total;

    public PaymentEntity(int id, String name, String address, String phone, String positionName, Double profit_tax, Double prof_tax, Double retirement_tax, LocalDate date, Double total) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.positionName = positionName;
        this.profit_tax = profit_tax;
        this.prof_tax = prof_tax;
        this.retirement_tax = retirement_tax;
        this.date = date;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getPositionName() {
        return positionName;
    }

    public Double getProfit_tax() {
        return profit_tax;
    }

    public Double getProf_tax() {
        return prof_tax;
    }

    public Double getRetirement_tax() {
        return retirement_tax;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getTotal() {
        return total;
    }

    public static Double countWorkedSalary(Double salary, Integer calendarDays, Integer workDays) {
        return salary / calendarDays * workDays;
    }
    public static Double countBenefitMoney(Double salary, Double benefitMoney) {
        return salary * benefitMoney / 100;
    }
    public static Double countTaxes(Double salary, Double bonus, Double profit_tax, Double prof_tax, Double retirement_tax) {
        return ((salary + bonus) * (profit_tax + prof_tax + retirement_tax)) / 100;
    }
    public static Double countActualSalary(Double salary, Double bonus, Double taxes) {
        return (salary + bonus) - taxes;
    }
}
