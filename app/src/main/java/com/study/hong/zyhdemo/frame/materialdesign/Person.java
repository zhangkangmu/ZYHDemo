package com.study.hong.zyhdemo.frame.materialdesign;

import java.io.Serializable;

/**
 * Created by hong on 2019/6/12.
 */

public class Person  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int imgId;

    public Person(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public int getImgId() {
        return imgId;
    }
}
