package com.shopperpos.movie;

import java.util.HashMap;

public class Test {

    void main(){
        HashMap<Integer, String> hashMap = new HashMap<>();
        int key = 2;
        String value = "Hola";
        addHashMap(hashMap, key, value);
    }

    private boolean search(HashMap<Integer, String> hashMap, int key){

        String array [] = new String[10];
        int count = array.length;

        if (hashMap != null){
            for (int a = 0; a < hashMap.size(); a++){
                if (hashMap.get(a).equals(key)){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean addHashMap(HashMap<Integer, String> hashMap, int key, String value){
        if (!search(hashMap, key)){
            hashMap.put(key, value);
            return true;
        }
        return false;
    }

}
