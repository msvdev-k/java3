package com.gb.less07;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Аннотация метода, который должен быть запущен при выполнении теста.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {

    /**
     * Приоритет выполнения теста от 1 (самый высокий)
     * до 10 (самый низкий). Значение по умолчанию 5.
     */
    int priority() default 5;

}
