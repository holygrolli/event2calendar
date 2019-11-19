package de.networkchallenge.e2c.lambda.backend.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class VersionResponse {
    private String timestamp;
    private String gitHash;
    private String timestampISO;

    public VersionResponse(String timestamp, String gitHash) {
        this.timestamp = timestamp;
        this.timestampISO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(timestamp)), ZoneId.of("Europe/Berlin")).toString();
        this.gitHash = gitHash;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getGitHash() {
        return gitHash;
    }

    public void setGitHash(String gitHash) {
        this.gitHash = gitHash;
    }

    public String getTimestampISO() {
        return timestampISO;
    }

    public void setTimestampISO(String timestampISO) {
        this.timestampISO = timestampISO;
    }
}
