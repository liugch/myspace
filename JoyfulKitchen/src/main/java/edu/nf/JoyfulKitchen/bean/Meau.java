package edu.nf.JoyfulKitchen.bean;

import java.util.Collection;
import java.util.Date;

/**
 * 菜谱
 */

public class Meau {

    private long id;

    private String name; // 菜名

    private String desc; // 描述

    private String img; // 菜图

    private Collection<FoodMaterial> foodMaterials; // 食材

    private Collection<Procedure> procedures;// 制作过程

    private Date createTime;// 创建时间

    private Date updateTime;// 更新时间


}
