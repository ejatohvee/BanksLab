package Banks.Clients;

import Exceptions.ClientException;

public class ClientBuilder {
    private String name;
    private String surname;
    private String address;
    private int passportNumber;
    private int passportSeries;

    public ClientBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public ClientBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public ClientBuilder withPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
        return this;
    }

    public ClientBuilder withPassportSeries(int passportSeries) {
        this.passportSeries = passportSeries;
        return this;
    }

    public Client Build() {
        if (name == null) {
            throw new ClientException("Client name cant be null");
        }
        if (surname == null) {
            throw new ClientException("Client surname cant be null");
        }
        return new Client(name, surname, address, passportNumber, passportSeries);
    }
}