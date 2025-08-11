package com.aya.meetingapp.MeetingBooking.dto;


public class MeetingRoomDto {

    private String roomName;
    private String roomLocation;
    private int capacity;

    public MeetingRoomDto() {}
    public MeetingRoomDto(String roomName, String roomLocation, int capacity ) {

        this.roomName = roomName;
        this.roomLocation = roomLocation;
        this.capacity = capacity;
    }

    public MeetingRoomDto(String roomLocation, int capacity, String roomName) {
    }


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}