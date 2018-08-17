package com.javaee.learning.demo2.controller;

import com.javaee.learning.demo2.pojo.Actor;
import com.javaee.learning.demo2.pojo.Student;
import com.javaee.learning.demo2.pojo.StudentList;
import com.javaee.learning.demo2.pojo.StudentWrapper;
import com.javaee.learning.demo2.service.StudentService;
import com.javaee.learning.demo2.utils.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class StudentController {
    @Resource(name = "studentService")
    private StudentService studentService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String findAll(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "studentList";
    }

    @RequestMapping("/student/add")
    public ModelAndView addStudent() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addStudent");
        return modelAndView;
    }

    @RequestMapping("/addStudent")
    public ModelAndView add(StudentWrapper wrapper) {
        System.out.println(wrapper);
        studentService.addStudent(wrapper.getStudent());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/testArray")
    public ModelAndView testArray(String[] names) {
        for (String name : names) {
            System.out.println(name);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/testList")
    public ModelAndView testList(StudentList studentList) {
        List<Student> students = studentList.getStudents();
        for (Student student : students) {
            System.out.println(student);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/testError")
    public void testError() throws Exception {
        throw new MyException("自定义异常测试！");
    }

    @RequestMapping("/json")
    @ResponseBody
    public Actor testJson(@RequestBody Actor actor) {
        System.out.println(actor);
        actor.setName("鞠婧祎");
        return actor;
    }

    @RequestMapping("/testRestful/{id}")
    @ResponseBody
    public Actor testRestful(@PathVariable("id") Integer integer) {
        System.out.println(integer);
        Actor actor = new Actor();
        actor.setId(integer);
        return actor;
    }
}
