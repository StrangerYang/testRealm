package com.example.testrealm;

import io.realm.RealmObject;

/**
 * @author Admin
 * https://realm.io/docs/java/latest/
 */
public class Dog extends RealmObject {

    String name;
    String age;
    String aihao;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getAihao() {
        return aihao;
    }

    public void setAihao(String aihao) {
        this.aihao = aihao;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", aihao='" + aihao + '\'' +
                '}';
    }
}
