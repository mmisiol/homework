package com.szalkowm.homework.application.rule;

public interface Rule<T> {
    void execute(T input);
}
