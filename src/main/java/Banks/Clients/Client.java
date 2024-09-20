package Banks.Clients;

import Observer.IObserver;

import java.util.UUID;

public class Client implements IObserver {
    private final UUID clientId;
    public String name;
    public String surname;
    public String address;
    private int passportNumber;
    private int passportSeries;

    public Client(String name, String surname, String address, int passportNumber, int passportSeries) {
        clientId = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passportNumber = passportNumber;
        this.passportSeries = passportSeries;
    }

    public void setPassportInfo(int passportNumber, int passportSeries) {
        this.passportNumber = passportNumber;
        this.passportSeries = passportSeries;
    }

    public boolean isSuspicious() {
        return passportSeries == 0 || address == null;
    }

    public UUID getClientId() {
        return clientId;
    }
    public void Update(String message) {}
}