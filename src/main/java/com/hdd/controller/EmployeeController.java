package com.hdd.controller;

import com.hdd.model.Employee;
import com.hdd.model.EmployeeForm;
import com.hdd.service.EmployeeService;
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
public class EmployeeController {
    @Autowired
    Environment env;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/create-employee")
    public ModelAndView showCreatForm() {
        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("employeeForm", new EmployeeForm());
        return modelAndView;
    }

    @PostMapping("/create-employee")
    public ModelAndView saveBook(@ModelAttribute EmployeeForm employeeForm) {
        MultipartFile multipartFile = employeeForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload1").toString();
        try {
            FileCopyUtils.copy(employeeForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Employee employee = new Employee(employeeForm.getName(), employeeForm.getBirthday(), employeeForm.getGender(), employeeForm.getAddress(), employeeForm.getPhone(), fileName);
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("employeeForm", new EmployeeForm());
        modelAndView.addObject("message", "New employee created successfully");
        return modelAndView;
    }

    @GetMapping("/list-employee")
    public ModelAndView listRoom() {
        Iterable<Employee> employees = employeeService.findAll();
        ModelAndView modelAndView = new ModelAndView("/employee/list-employee");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/edit-employee/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            ModelAndView modelAndView = new ModelAndView("/employee/edit");
            EmployeeForm employeeForm = new EmployeeForm(employee.getName(), employee.getBirthday(), employee.getGender(), employee.getAddress(), employee.getPhone(), null);
            modelAndView.addObject("employee", employee);
            modelAndView.addObject("employeeForm", employeeForm);
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/edit-employee")
    public ModelAndView updateBook(@ModelAttribute EmployeeForm employeeForm) {
        MultipartFile multipartFile = employeeForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(employeeForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Employee employee = new Employee(employeeForm.getId(), employeeForm.getName(), employeeForm.getBirthday(), employeeForm.getGender(), employeeForm.getAddress(), employeeForm.getPhone(), fileName);
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("/employee/edit");
        modelAndView.addObject("employeeForm", employeeForm);
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("message", "Edit successfully!");
        return modelAndView;
    }

    @GetMapping("/delete-employee/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            ModelAndView modelAndView = new ModelAndView("/employee/delete");
            modelAndView.addObject("employee", employee);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-employee")
    public String deleteCustomer(@ModelAttribute("employee") Employee employee) {
        employeeService.remove(employee.getId());
        return "redirect:list-employee";
    }

}
