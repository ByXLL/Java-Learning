package com.interface_demo;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Hashtable;

public class TestDemo {
    public static void main(String[] args) {
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        PhantomReference<String> str = new PhantomReference<String>("abc", queue);
        System.out.println(str.get());
    }
}
