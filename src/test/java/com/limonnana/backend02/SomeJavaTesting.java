package com.limonnana.backend02;

public class SomeJavaTesting {

    public static void main(String[] args) {
        SomeJavaTesting s = new SomeJavaTesting();
        Dog dog = new Dog("Shmuel");
       // Dog d = s.setDogsName(dog);
       // s.foo(dog);
     //   System.out.println(dog.name);
       // System.out.println(d.name);
        Integer i = 4;
       s.foo(i);
        System.out.println(i);
      //  s.tryThis(dog);
      //  System.out.println(dog.name);
    }

    public void foo(Integer i){
        int k =10000;
        i = k = 8;
       // System.out.println(i);
    }

    public void foo(Dog someDog) {  // AAA
        someDog.setName("Max");     // BBB
        someDog = new Dog("Fifi");  // CCC
        someDog.setName("Rowlf");   // DDD
    }

    void tryThis(Dog d){
        d.setName("Osvaldo");
    }

    Dog setDogsName(Dog d){
        Dog dog1 = new Dog("Osvaldo");
        dog1.name = "Raul" + d.name;
       return dog1;
    }

    static class Dog{
        String name;
        Dog(String name){
            this.name = name;
        }
        void setName(String name){
            this.name = name;
        }
    }
}
