package com.example.healthiu.service.impl;

import com.example.healthiu.entity.Gender;
import com.example.healthiu.entity.Test;
import com.example.healthiu.entity.TestData;
import com.example.healthiu.entity.TestResult;
import com.example.healthiu.repository.TestRepository;
import com.example.healthiu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

    @Override
    public boolean checkIfTestExistsByUserLogin(String userLogin) {
        return testRepository.findById(userLogin).isPresent();
    }

    @Override
    public double findBmi(TestData testData) {
        return testData.getWeight() / Math.pow(testData.getHeight() / 100, 2);
    }

    @Override
    public String calculateResult(TestData testData) {
        int bmiCo = calculateBmi(testData);
        int waistToHipRatioCo = calculateWaistToHipRatio(testData);
        int waistToHeightRatioCo = calculateWaistToHeightRatio(testData);
        int waistRatio = calculateWaistRatio(testData);
        int fatRatio = calculateBodyFatRatio(testData);
        int chestToWaistRatio = calculateChestToWaistRatio(testData);
        int sumCo = bmiCo + waistToHipRatioCo;

//        if (sumCo == 0) {
//            return TestResult.UNDERWEIGHT.getTestResult();
//        }
//
//        if (sumCo == 3) {
//            return TestResult.OVERWEIGHT.getTestResult();
//        }
//
//        if (sumCo == 4) {
//            return TestResult.OBESE.getTestResult();
//        }
//
//        if (sumCo == 5) {
//            return TestResult.EXTREMELY_OBESE.getTestResult();
//        }
//
//        if (sumCo == 6) {
//            return TestResult.OBESE_AS_HELL.getTestResult();
//        }
//
//        return TestResult.NORMAL.getTestResult();
        return "WaistToHip = " + waistToHipRatioCo + "\nWaistToHeight = " + waistToHeightRatioCo +
                "\nWaist = " + waistRatio + "\nFat = " + fatRatio + "\nChestToWaist = " + chestToWaistRatio;
    }

    @Override
    public boolean isTestDataValid(TestData testData, String userLogin) {
        try {
            Test test = new Test(userLogin,
                    testData.getGender(),
                    testData.getAge(),
                    testData.getHeight(),
                    testData.getWeight(),
                    testData.getWaistSize(),
                    testData.getChestSize(),
                    testData.getHipSize(),
                    testData.getBloodType());
            System.out.println(test.getHeight());
            testRepository.save(test);
        } catch (Exception ex) {
            System.out.println("bad test");
            System.out.println(ex.getMessage());
            return false;
        }
        System.out.println("good test");
        return true;
    }




    /////////////////// CALCULATIONS
    private int calculateBmi(TestData testData) {
        int obesity = 0;

        double bmi = findBmi(testData);
        double minBmi = bmiForAge(testData.getAge(), 19);
        double maxBmi = bmiForAge(testData.getAge(), 24);
        double bmiDifference = 5;

        if (bmi < minBmi) {
            obesity = -1;
        }

        if (bmi >= maxBmi && bmi < maxBmi + bmiDifference) {
            obesity = 1;
        }

        if (bmi >= maxBmi + bmiDifference && bmi < maxBmi + bmiDifference * 2) {
            obesity = 2;
        }

        if (bmi >= maxBmi + bmiDifference * 2 && bmi < maxBmi + bmiDifference * 3) {
            obesity = 3;
        }

        if (bmi >= maxBmi + bmiDifference * 3) {
            obesity = 4;
        }

        return obesity;
    }

    private int calculateWaistToHipRatio(TestData testData) {
        double ratio = testData.getWaistSize() / testData.getHipSize();
        int obesity = 0;
        double maleBias = 0.9;
        double femaleBias = 0.8;
        double difference = 0.05;

        if (testData.getGender().equals(Gender.MALE.getGender())) {
            obesity = getObesity(ratio, obesity, maleBias, difference);
        } else {
            obesity = getObesity(ratio, obesity, femaleBias, difference);
        }
        return obesity;
    }

    private int calculateWaistToHeightRatio(TestData testData) {
        double ratio = testData.getWaistSize() / testData.getHeight();
        System.out.println(ratio);
        System.out.println(testData.getWaistSize());
        System.out.println(testData.getHeight());
        int obesity = 0;

        if (testData.getAge() <= 15) {
            if (ratio < 0.34) {
                obesity = -2;
            }
            if (ratio >= 0.34 && ratio < 0.46) {
                obesity = -1;
            }
            if (ratio >= 0.52 && ratio < 0.64) {
                obesity = 1;
            }
            if (ratio >= 0.64) {
                obesity = 2;
            }
        } else {
            if (testData.getGender().equals(Gender.MALE.getGender())) {
                if (ratio < 0.35) {
                    obesity = -2;
                }
                if (ratio >= 0.35 && ratio < 0.43) {
                    obesity = -1;
                }
                if (ratio >= 0.53 && ratio < 0.58) {
                    obesity = 1;
                }
                if (ratio >= 0.58 && ratio < 0.63) {
                    obesity = 2;
                }
                if (ratio >= 0.63) {
                    obesity = 3;
                }
            } else {
                if (ratio < 0.35) {
                    obesity = -2;
                }
                if (ratio >= 0.35 && ratio < 0.42) {
                    obesity = -1;
                }
                if (ratio >= 0.49 && ratio < 0.54) {
                    obesity = 1;
                }
                if (ratio >= 0.54 && ratio < 0.58) {
                    obesity = 2;
                }
                if (ratio >= 0.58) {
                    obesity = 3;
                }
            }
        }
        return obesity;
    }

    private int calculateWaistRatio (TestData testData) {
        int obesity = 0;
        double waistSize = testData.getWaistSize();

        if (testData.getGender().equals(Gender.MALE.getGender())) {
            if (waistSize > 94 && waistSize <= 100) {
                obesity = 1;
            }
            if (waistSize > 100) {
                obesity = 2;
            }
        } else {
            if (waistSize > 80 && waistSize <= 88) {
                obesity = 1;
            }
            if (waistSize > 88) {
                obesity = 2;
            }
        }

        return obesity;
    }

    private int calculateBodyFatRatio (TestData testData) {
        int obesity = 0;
        double hipBias = 76;
        double heightBias = 188;
        double fatBias = 4;

        double hipSize = testData.getHipSize();
        double height = testData.getHeight();
        double hipDifference = (hipSize >= hipBias) ? hipSize - hipBias : hipBias - hipSize;
        double heightDifference = (height <= heightBias) ? heightBias - height : height - heightBias;
        double fat = fatBias + (hipDifference + heightDifference) / 2;

        if (testData.getGender().equals(Gender.MALE.getGender())) {
            if (fat < 5) {
                obesity = -2;
            }
            if (fat >= 5 && fat < 8) {
                obesity = -1;
            }
            if (fat >= 19 && fat < 25 ) {
                obesity = 1;
            }
            if (fat >= 25) {
                obesity = 2;
            }
        } else {
            if (fat < 18) {
                obesity = -2;
            }
            if (fat >= 18 && fat < 21) {
                obesity = -1;
            }
            if (fat >= 33 && fat < 39 ) {
                obesity = 1;
            }
            if (fat >= 39) {
                obesity = 2;
            }
        }
        return obesity;
    }

    private int calculateChestToWaistRatio(TestData testData) {
        int obesity = 0;
        if (testData.getChestSize() <= testData.getWaistSize()) {
            obesity = 3;
        }
        return obesity;
    }
    /////////////////// CALCULATIONS




    //////// CALCULATION HELPERS
    private double bmiForAge(int age, double bmi) {
        for (int i = 14; i < 130; i++) {
            if (i == 25) {
                bmi++;
            }
            if (i == 35) {
                bmi++;
            }
            if (i == 45) {
                bmi++;
            }

            if (i == 55) {
                bmi++;
            }

            if (i == 65) {
                bmi++;
            }
            if (age == i) {
                return bmi;
            }
        }
        return bmi;
    }

    private int getObesity(double ratio, int obesity, double maleBias, double difference) {
        if (ratio > maleBias && ratio <= maleBias + difference) {
            obesity = 1;
        }
        if (ratio > maleBias + difference && ratio <= maleBias + difference * 2) {
            obesity = 2;
        }
        if (ratio > maleBias + difference * 2) {
            obesity = 3;
        }
        return obesity;
    }
}
