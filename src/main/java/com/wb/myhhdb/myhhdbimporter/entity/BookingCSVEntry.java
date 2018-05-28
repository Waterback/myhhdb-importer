package com.wb.myhhdb.myhhdbimporter.entity;

import sun.plugin2.message.Message;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;

@Entity
public class BookingCSVEntry {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date booking;

    private String accountNr;

    private Date valuta;

    private String recipient;

    private String text;

    private String reason;

    private Double saldo;

    private Currency currencySaldo;

    private Double moneyMoved;

    private Currency currencyMM;

    @Column(unique = true)
    private String hashValue;

    public BookingCSVEntry() {}

    public BookingCSVEntry(Date booking, Date valuta, String recipient, String text, String reason, Double saldo, Currency currencySaldo, Double moneyMoved, Currency currencyMM, String accountNr) {

        this.booking = booking;
        this.valuta = valuta;
        this.recipient = recipient;
        this.text = text;
        this.reason = reason;
        this.saldo = saldo;
        this.currencySaldo = currencySaldo;
        this.moneyMoved = moneyMoved;
        this.currencyMM = currencyMM;
        this.accountNr = accountNr;
        try {
            this.hashValue = generateHashValue();
        } catch (Exception e) {
            this.hashValue = getStringBuffer().toString();
        }
    }

    private String generateHashValue() throws Exception {
        StringBuffer appender = getStringBuffer();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update (appender.toString().getBytes());
        byte[] digested = md5.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b: digested) {
            sb.append(b);
        }
        return sb.toString();

    }

    private StringBuffer getStringBuffer() {
        StringBuffer appender = new StringBuffer(getBooking().toString());
        appender.append(getValuta().toString());
        appender.append(getRecipient());
        appender.append(getText());
        appender.append(getReason());
        appender.append(getSaldo());
        appender.append(getMoneyMoved());
        appender.append(getAccountNr());
        System.out.println(appender.toString());
        return appender;
    }


    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public String getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(String accountNr) {
        this.accountNr = accountNr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBooking() {
        return booking;
    }

    public void setBooking(Date booking) {
        this.booking = booking;
    }

    public Date getValuta() {
        return valuta;
    }

    public void setValuta(Date valuta) {
        this.valuta = valuta;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Currency getCurrencySaldo() {
        return currencySaldo;
    }

    public void setCurrencySaldo(Currency currencySaldo) {
        this.currencySaldo = currencySaldo;
    }

    public Double getMoneyMoved() {
        return moneyMoved;
    }

    public void setMoneyMoved(Double moneyMoved) {
        this.moneyMoved = moneyMoved;
    }

    public Currency getCurrencyMM() {
        return currencyMM;
    }

    public void setCurrencyMM(Currency currencyMM) {
        this.currencyMM = currencyMM;
    }

    @Override
    public String toString() {
        return valuta.toString() + "/" + saldo  + "/" +
                moneyMoved + "/" + text + "/" + reason;
    }
}
