package com.example.healthiu.entity;

public enum TestResult {
    UNDERWEIGHT("You are underweight. It is recommended to gain weight."),
    NORMAL("Your weight is normal. You are fine just maintaining your weight."),
    OVERWEIGHT("You are overweight. It is recommended to lose weight."),
    OBESE("You are obese. It is strongly recommended to lose weight. Please, take care of your health."),
    EXTREMELY_OBESE("You are extremely obese. Please, consult your doctor and seek help. "),
    MORBIDLY_OBESE("You are morbidly obese. You have a great death possibility. It is important to use medical " +
            "help in your state."),


    RATION_O_STOP_GAIN("Пшениця (клейковина), Кукурудза, Звичайна овочева квасоля, Боби темні, Сочевиця, " +
            "Кочанна капуста, Брюссельська капуста, Цвітна капуста, Молоде листя гірчиці"),
    RATION_O_STOP_LOSE("Печінка, Червоне м'ясо, Капуста листова, капуста спаржева (брокколі), шпинат."),
    RATION_O_LOSE("Бура водорость, Морські продукти, Йодована сіль, Печінка, Червоне м'ясо, Капуста листова, " +
            "капуста спаржева (брокколі), шпинат."),
    RATION_O_GAIN("Пшениця (клейковина), Кукурудза, Звичайна овочева квасоля, Боби темні, Сочевиця, Морські продукти."),

    RATION_A_STOP_GAIN("М'ясо, Молочна їжа, Звичайна овочева квасоля, Лімська квасоля, Пшениця (при споживанні понад міру)."),
    RATION_A_STOP_LOSE("Соєві продукти, Овочі, Ананаси."),
    RATION_A_LOSE("Рослинні олії, Соєві продукти, Овочі, Ананаси."),
    RATION_A_GAIN("М'ясо, Молочна їжа, Звичайна овочева квасоля, Лімська квасоля, Рослинні олії."),

    RATION_B_STOP_GAIN("Кукурудза, Сочевиця, Земляні горіхи (арахіс), Насіння кунжуту, Гречка, Пшениця."),
    RATION_B_STOP_LOSE("Зелені овочі, М'ясо, Яйця, Молочні продукти (з низькою жирністю), Печінка І лівер."),
    RATION_B_LOSE("Зелені овочі, М'ясо, Яйця, Молочні продукти (з низькою жирністю), Печінка І лівер, " +
            "Настій (чай) з солодкового (лакричного) кореня."),
    RATION_B_GAIN("Пшениця, Гречка, Настій (чай) з солодкового (лакричного) кореня."),

    RATION_AB_STOP_GAIN("Червоне м'ясо, Звичайна овочева квасоля, Лімська квасоля, Насіння, Кукурудза, Гречка, Пшениця."),
    RATION_AB_STOP_LOSE("Тофу, Морські продукти, Зелені овочі, Ананаси."),
    RATION_AB_LOSE("Тофу, Морські продукти, Молочні продукти, Зелені овочі, Бура водорость, Ананаси."),
    RATION_AB_GAIN("Кукурудза, Гречка, Пшениця, Молочні продукти."),


    CALORIES_TO_MAINTAIN("For maintaining your current weight you need this amount of calories per day: "),
    CALORIES_TO_LOSE_05("For losing 0.5 kg per week you need this amount of calories per day: "),
    CALORIES_TO_LOSE_1("For losing 1 kg per week you need this amount of calories per day: "),
    CALORIES_TO_GAIN_05("For gaining 0.5 kg per week you need this amount of calories per day: "),
    CALORIES_TO_GAIN_1("For gaining 1 kg per week you need this amount of calories per day: ")
    ;
    private final String testResult;

    TestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getTestResult() {
        return testResult;
    }
}
