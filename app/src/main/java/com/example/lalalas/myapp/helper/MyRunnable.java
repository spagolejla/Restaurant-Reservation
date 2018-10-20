package com.example.lalalas.myapp.helper;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public interface  MyRunnable<T> extends Serializable {

     void  run(T t);

}
