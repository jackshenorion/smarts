package com.jackshenorion.smarts.util.mapper;

public class SampleMessage {
    private String name;
    private String address;
    private String city;

    public String getName() {
        return name;
    }

    public SampleMessage setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SampleMessage setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public SampleMessage setCity(String city) {
        this.city = city;
        return this;
    }

    @Override
    public String toString() {
        return "SampleMessage{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
