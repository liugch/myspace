package edu.nf.JoyfulKitchen.bean;

import java.util.Collection;
import java.util.Date;

/**
 * 食材类型
 */

public class FoodType {

    private long id;
    private String name; // 类型名称

    private Collection<FoodMaterial> foodMaterials; // 具有哪些的食材

    private Date createTime;

    private Date updateTime;

}
