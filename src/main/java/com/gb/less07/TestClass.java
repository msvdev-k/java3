package com.gb.less07;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Класс, который может выполнять «тесты».
 */
public class TestClass {

    /**
     * Класс с тестами.
     */
    private static Class<?> classUnderTest;

    /**
     * Метод выполняемый до запуска всех тестов.
     */
    private static Method beforeSuiteMethod = null;

    /**
     * Метод выполняемый после завершения всех тестов.
     */
    private static Method afterSuiteMethod = null;

    /**
     * Методы выполняемые во время тестирования.
     * Ключ - приоритет выполнения метода,
     * значение - список методов.
     */
    private static final Map<Integer, List<Method>> testMethods = new TreeMap<>();


    /**
     * Метод, запускающий тестовый класс.
     *
     * @param classUnderTestName полное имя класса с тестами.
     */
    public static void start(String classUnderTestName) {
        try {
            start(Class.forName(classUnderTestName));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод, запускающий тестовый класс.
     *
     * @param classUnderTest класс с тестами.
     */
    public static void start(Class<?> classUnderTest) {
        clear();

        TestClass.classUnderTest = classUnderTest;

        Method[] methods = classUnderTest.getMethods();

        for (Method m : methods) {

            if (m.getAnnotation(BeforeSuite.class) != null) {

                if (beforeSuiteMethod == null) {
                    beforeSuiteMethod = m;
                } else {
                    throw new RuntimeException("Метод с аннотацией @BeforeSuite должен присутствовать в единственном экземпляре");
                }
            } else if (m.getAnnotation(AfterSuite.class) != null) {

                if (afterSuiteMethod == null) {
                    afterSuiteMethod = m;
                } else {
                    throw new RuntimeException("Метод с аннотацией @AfterSuite должен присутствовать в единственном экземпляре");
                }
            } else {

                Test annotation = m.getAnnotation(Test.class);

                if (annotation != null) {

                    int priority = annotation.priority();

                    if (priority < 1 || priority > 10) {
                        throw new RuntimeException("Приоритет выполнения теста должен быть в диапазоне от 1 до 10 включительно");
                    }

                    if (!testMethods.containsKey(priority)) {
                        testMethods.put(priority, new ArrayList<>());
                    }

                    List<Method> methodList = testMethods.get(priority);
                    methodList.add(m);
                }
            }
        }

        test();
    }


    /**
     * Метод, выполняющий тестирование.
     */
    private static void test() {

        try {

            Object classUnderTestObject = classUnderTest.newInstance();

            if (beforeSuiteMethod != null) {
                beforeSuiteMethod.invoke(classUnderTestObject);
            }

            for (Map.Entry<Integer, List<Method>> entry : testMethods.entrySet()) {
                for (Method m : entry.getValue()) {
                    m.invoke(classUnderTestObject);
                }
            }

            if (afterSuiteMethod != null) {
                afterSuiteMethod.invoke(classUnderTestObject);
            }

        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

    }


    /**
     * Очистить класс с тестами.
     */
    private static void clear() {
        classUnderTest = null;
        beforeSuiteMethod = null;
        afterSuiteMethod = null;
        testMethods.clear();
    }

}
