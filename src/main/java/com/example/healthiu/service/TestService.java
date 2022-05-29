package com.example.healthiu.service;

import com.example.healthiu.entity.TestData;

public interface TestService {
    boolean checkIfTestExistsByUserLogin(String userLogin);

    double findBmi(TestData testData);

    String calculateResult(TestData testData);

    boolean isTestDataValid(TestData testData, String userLogin);
}
