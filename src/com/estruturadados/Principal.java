package com.estruturadados;

public class Principal {

    public static void main(String[] args){

        No no1 = new No("no1");
        No no2 = new No("no2");
        no1.setProximo(no2);

        No no3 = new No("no3");
        no2.setProximo(no3);

        No no4 = new No("no4");
        no3.setProximo(no4);

    }
}
