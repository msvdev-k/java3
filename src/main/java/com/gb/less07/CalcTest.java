package com.gb.less07;


/**
 * Класс для тестирования калькулятора.
 */
public class CalcTest {

    private Calculator calculator;


    @BeforeSuite
    public void beforeSuite() {

        calculator = new Calculator();

        System.out.println("Тестирование запущено");
        System.out.println("=====================");
        System.out.println();
    }


    @AfterSuite
    public void afterSuite() {

        System.out.println();
        System.out.println("Тестирование закончено");
        System.out.println("======================");
    }


    @Test(priority = 6)
    public void testAdd() {

        if (calculator.add(2, 2) == 4) {
            System.out.println("add method - OK");
        }
        else {
            System.out.println("add method - error");
        }
    }


    @Test(priority = 8)
    public void testSub() {

        if (calculator.sub(5, 2) == 3) {
            System.out.println("sub method - OK");
        }
        else {
            System.out.println("sub method - error");
        }
    }


    @Test(priority = 1)
    public void testMul() {

        if (calculator.mul(3, 3) == 9) {
            System.out.println("mul method - OK");
        }
        else {
            System.out.println("mul method - error");
        }
    }


    @Test(priority = 4)
    public void testDiv() {

        if (calculator.div(2, 2) == 1) {
            System.out.println("div method - OK");
        }
        else {
            System.out.println("div method - error");
        }
    }

}
