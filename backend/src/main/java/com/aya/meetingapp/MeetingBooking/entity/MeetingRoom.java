package com.aya.meetingapp.MeetingBooking.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class MeetingRoom {
    @Id
    @SequenceGenerator(
            name = "sequence_name",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )

    private Long roomId;
    private String roomName;
    private String roomLocaion; //which building and floor
    private int capacity;

    @OneToMany(mappedBy ="meetingRoom", cascade= CascadeType.ALL , orphanRemoval = true)
    private List<Reservation> reservations_list = new ArrayList<>();

    public MeetingRoom() {
    }

    public MeetingRoom(Long roomId, String roomName, String roomLocaion, int capacity) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomLocaion = roomLocaion;
        this.capacity = capacity;
    }

    public MeetingRoom(String roomName, String roomLocaion, int capacity) {
        this.roomName = roomName;
        this.roomLocaion = roomLocaion;
        this.capacity = capacity;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomLocaion() {
        return roomLocaion;
    }

    public void setRoomLocaion(String roomLocaion) {
        this.roomLocaion = roomLocaion;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Reservation> getReservations_list() {
        return reservations_list;
    }

    public void setReservations_list(List<Reservation> reservations_list) {
        this.reservations_list = reservations_list;
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", roomLocaion='" + roomLocaion + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
