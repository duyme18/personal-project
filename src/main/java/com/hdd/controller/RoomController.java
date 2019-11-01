package com.hdd.controller;

import com.hdd.model.Room;
import com.hdd.model.RoomForm;
import com.hdd.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
public class RoomController {
    @Autowired
    Environment env;
    @Autowired
    private RoomService roomService;

    @GetMapping("/create-room")
    public ModelAndView showCreatForm() {
        ModelAndView modelAndView = new ModelAndView("/room/create");
        modelAndView.addObject("roomForm", new RoomForm());
        return modelAndView;
    }

    @PostMapping("/create-room")
    public ModelAndView saveBook(@ModelAttribute RoomForm roomForm) {
        MultipartFile multipartFile = roomForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(roomForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Room room = new Room(roomForm.getName(), roomForm.getPrice(), roomForm.getStatus(), fileName);
        roomService.save(room);
        ModelAndView modelAndView = new ModelAndView("/room/create");
        modelAndView.addObject("roomForm", new RoomForm());
        modelAndView.addObject("message", "New room created successfully");
        return modelAndView;
    }

    @GetMapping("/list-room")
    public ModelAndView listRoom() {
        Iterable<Room> rooms = roomService.findAll();
        ModelAndView modelAndView = new ModelAndView("/room/list-room");
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @GetMapping("/edit-room/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Room room = roomService.findById(id);
        if (room != null) {
            ModelAndView modelAndView = new ModelAndView("/room/edit");
            RoomForm roomForm = new RoomForm(room.getName(), room.getPrice(), room.getStatus(), null);
            modelAndView.addObject("room", room);
            modelAndView.addObject("roomForm", roomForm);
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/edit-room")
    public ModelAndView updateBook(@ModelAttribute RoomForm roomForm) {
        MultipartFile multipartFile = roomForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(roomForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Room room = new Room(roomForm.getId(), roomForm.getName(), roomForm.getPrice(), roomForm.getStatus(), fileName);
        roomService.save(room);
        ModelAndView modelAndView = new ModelAndView("/room/edit");
        modelAndView.addObject("roomForm", roomForm);
        modelAndView.addObject("room", room);
        modelAndView.addObject("message", "Edit successfully!");
        return modelAndView;
    }

    @GetMapping("/delete-room/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Room room = roomService.findById(id);
        if (room != null) {
            ModelAndView modelAndView = new ModelAndView("/room/delete");
            modelAndView.addObject("room", room);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-room")
    public String deleteCustomer(@ModelAttribute("room") Room room) {
        roomService.remove(room.getId());
        return "redirect:list-room";
    }
}
