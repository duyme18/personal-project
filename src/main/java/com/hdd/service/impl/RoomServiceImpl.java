package com.hdd.service.impl;

import com.hdd.model.Room;
import com.hdd.repository.RoomRepository;
import com.hdd.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findOne(id);
    }

    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void remove(Long id) {
        roomRepository.delete(id);
    }
}
