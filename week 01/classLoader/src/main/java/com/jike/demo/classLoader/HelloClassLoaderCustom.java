package com.jike.demo.classLoader;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoaderCustom extends ClassLoader{

    public static void main(String[] args) {
        try {
            Class<?> helloClass = new HelloClassLoaderCustom().findClass("Hello");
            Method helloMethod = helloClass.getMethod("hello");
            helloMethod.invoke(helloClass.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = null;
        try {
            Resource resource = new ClassPathResource("Hello.xlass");
            InputStream is = resource.getInputStream();
            bytes = readFileBytes(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] readFileBytes(InputStream is){
        byte[] data = null;
        try {
            if(is.available()==0){
                return data;
            }
            data = new byte[is.available()];
            is.read(data);
            is.close();
            return data;
        } catch (IOException e) {
            return data;
        }
    }

}
