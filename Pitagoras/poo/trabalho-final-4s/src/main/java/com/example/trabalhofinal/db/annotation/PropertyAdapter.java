package com.example.trabalhofinal.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.trabalhofinal.db.adapter.Adapter;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PropertyAdapter {

	Class<? extends Adapter<?>> adapter();
}
