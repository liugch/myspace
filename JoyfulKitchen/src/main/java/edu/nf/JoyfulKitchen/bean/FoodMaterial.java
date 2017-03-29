package edu.nf.JoyfulKitchen.bean;

import java.util.Collection;
import java.util.Date;

/**
 *  食材
 */

public class FoodMaterial {

    private long id;

    private String name; // 食材名称

    private double protein;//蛋白质

    private String proteinUnit; //单位

    private double fat;// 脂肪

    private String fatUnit;

    private double carbohydrate;// 碳水化合物

    private String carbohydrateUnit;

    private FoodType foodType; // 食材类型

    private Collection<Meau> meaus; // 具有的菜

    private Date createTime;

    private Date updateTime;

}
