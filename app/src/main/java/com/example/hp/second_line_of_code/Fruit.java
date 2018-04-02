package com.example.hp.second_line_of_code;

/**
 * @author liz
 * @version V1.0
 * @date 2018/3/27
 */

public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
