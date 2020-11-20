package ru.mtuci.simpleapiiuk.dto;

public class Status {
    private final String hostName;

    public Status(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return hostName;
    }
}
