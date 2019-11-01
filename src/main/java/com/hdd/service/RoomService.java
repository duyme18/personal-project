package com.hdd.service;

import com.hdd.model.Room;

public interface RoomService {
    Iterable<Room> findAll();

    Room findById(Long id);

    void save(Room room);

    void remove(Long id);
}
