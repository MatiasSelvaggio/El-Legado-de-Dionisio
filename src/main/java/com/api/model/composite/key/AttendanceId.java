package com.api.model.composite.key;

import java.io.Serializable;
import java.util.Objects;

public class AttendanceId implements Serializable {

    private Long event;
    private Long user;

    public AttendanceId() {}

    public AttendanceId(Long event, Long user) {
        this.event = event;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceId that = (AttendanceId) o;
        return Objects.equals(event, that.event) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event, user);
    }
}
