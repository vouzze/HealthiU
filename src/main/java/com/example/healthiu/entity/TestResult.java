package com.example.healthiu.entity;

public enum TestResult {
    UNDERWEIGHT("You are underweight. It is recommended to gain weight."),
    NORMAL("Your weight is normal. You are fine just maintaining your weight."),
    OVERWEIGHT("You are overweight. It is recommended to lose weight."),
    OBESE("You are obese. It is strongly recommended to lose weight. Please, take care of your health."),
    EXTREMELY_OBESE("You are extremely obese. Please, consult your doctor and seek help. "),
    OBESE_AS_HELL("You are obese as hell. LOSE WEIGHT OR DIE!")
    ;

    private final String testResult;

    TestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getTestResult() {
        return testResult;
    }
}
