package com.example.lalalas.myapp.helper;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;

public abstract class  MyRunnable<T> implements Serializable {

    public abstract void  run(T t) throws ParseException;

    public Class<T> getGenericType()
    {
        Class<T> persistentClass = (Class<T>)
                ((ParameterizedType)getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];

        return persistentClass;
    }
}
