package com.example.healthiu.controller;

import com.example.healthiu.entity.BloodType;
import com.example.healthiu.entity.Gender;
import com.example.healthiu.entity.TestData;
import com.example.healthiu.entity.UserData;
import com.example.healthiu.service.TestService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String getTest(Model model) {
        TestData testData = new TestData();
        testData.setGender(Gender.MALE.getGender());
        model.addAttribute("testData", testData);
        model.addAttribute("allGenders", Stream.of(Gender.values())
                .map(Gender::getGender)
                .collect(Collectors.toList()));
        model.addAttribute("allBloodTypes", Stream.of(BloodType.values())
                .map(BloodType::getBloodType)
                .collect(Collectors.toList()));
        return "test";
    }

    @PostMapping("/test/result")
    public String userRegistration(@Valid TestData testData, BindingResult result, HttpServletRequest req, Model model) {
//        String userLogin = req.getRemoteUser();
//        boolean isValid = testService.isTestDataValid(testData, userLogin);
//        System.out.println(isValid);
        double bmi = testService.findBmi(testData);
        model.addAttribute("bmi", String.format("%.1f", bmi));
        model.addAttribute("resultBmi", testService.calculateResult(testData));
        return "test_result";
    }

    @GetMapping("/test/result")
    public String getResult() {
        return "redirect:/test";
    }
}
