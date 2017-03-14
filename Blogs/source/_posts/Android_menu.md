---
title: Android Menu
date: 2016-09-26 12:50:1
categories: "Android" 
tags: [Android]
comments: true
---
# Meau #
> 选项菜单

> 上下文菜单

> 子菜单

> Popup弹出菜单

> ActionMode 创建导航菜单

以下小例子都有很多优化的地方

<!--more-->

**选项菜单**

onCreateOptionsMenu(Menu menu)

onOptionsItemSelected(MenuItem item)
	
**showAsAction 在as 中 会报错  要添加 xmlns:app="http://schemas.android.com/apk/res-auto"  引用**

    <?xml version="1.0" encoding="utf-8"?>
	<menu xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto" >
	    <!--
	        item 中通还可以有menu  : 表示的为 子菜单;
	        menuCategroy:属性可以控制菜单项的位置
	        orderInCategory 同种类菜单进行排序
	        title: 菜单显示的文本
	        titleCondensed：菜单项的短标题。当菜单项标题太长时会显示该属性值
	        icon: 设置图标
	        alphabeticShortcut:添加菜单项的字母快捷键
	        numericShortcut：添加菜单项的数字快捷键
	        checkable：表示菜单项是否带复选框。该属性可设计为true或false
	        checked：如果菜单项带复选框(checkable属性为true)，该属性表示复选框默认状态是否被选中。可设置的值为true或false
	        visible：菜单项默认状态是否可视 true|false
	        enable：菜单项默认状态是否被激活 是否是可用的
	        showAsAction:
	            ifRoom: 如果溢出就会隐藏 溢出来的部分
	            never:永远不会显示。只会在溢出列表中显示，而且只显示标题，所以在定义item的时候，最好把标题都带上。
	            always : 不管是是否溢出都会显示
	            withText: 只显示文本
	            collapseActionView:声明了这个操作视窗应该被折叠到一个按钮中，当用户选择这个按钮时，
	                        这个操作视窗展开。否则，这个操作视窗在默认的情况下是可见的，
	                        并且即便在用于不适用的时候，也要占据操作栏的有效空间。
	                        一般要配合ifRoom一起使用才会有效果。
	   	 -->
	    <item
	        android:id="@+id/action_add"
	        android:orderInCategory="1"
	        app:showAsAction="never"
	        android:title="呵呵哒" />
	
	    <item
	        android:id="@+id/delete"
	        android:orderInCategory="2"
	        app:showAsAction="never"
	        android:title="萌萌哒" />
	</menu>
	

----------
	
       ///////////////////  创建一个选项菜单
    /**
     * 创建一个选项菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
	//        menu.add(1,1,1,"add");
	//        menu.add(1,2,1,"delete");
        return true;
    }
    /**
     * 当菜单选项被单击的时候
     * @param
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                toast("add");
                break;
            case R.id.delete:
                toast("delete");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    
  

**上下文菜单**

    <?xml version="1.0" encoding="utf-8"?>
	<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/a"
        android:title="a"
        android:orderInCategory="1"
        app:showAsAction="ifRoom"
        />
    <item
        android:id="@+id/b"
        android:title="b"
        android:orderInCategory="2"
        app:showAsAction="ifRoom"
        />
    <item
        android:id="@+id/c"
        android:title="c"
        android:orderInCategory="3"
        app:showAsAction="ifRoom"
        />
</menu>

----------

    list = (ListView) findViewById(R.id.listview);
        String[] listItem = {"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adpater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItem);
        list.setAdapter(adpater);

        registerForContextMenu(list);//为 listview 注册一个菜单

	///////              创建一个上下文菜单
    /**
     * 导入你所需的菜单
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.item, menu);
    }

    /**
     * 当 listview中的item 长按的时候
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.a:
                toast("a");
                break;
            case R.id.b:
                toast("b");
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }


**子菜单**

     /////////////////////////////// 创建子菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu a = menu.addSubMenu(1, 1, 1, "hehe");
        a.setHeaderTitle("想你了");
        a.setHeaderIcon(R.mipmap.ic_launcher);
        a.add(1, 1, 1, "a");
        a.add(1, 2, 1, "b");
        SubMenu b = menu.addSubMenu(2, 1, 1, "heihei");
        b.setHeaderTitle("不想你了");
        b.add(2, 1, 1, "c");
        b.add(2, 2, 1, "d");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getGroupId() == 1) {
            switch (item.getItemId()) {
                case 1:
                    toast("a");
                    break;
                case 2:
                    toast("b");
                    break;
                default:
                    break;
            }
        }else if (item.getGroupId() == 2) {
            switch (item.getItemId()) {
                case 1:
                    toast("c");
                    break;
                case 2:
                    toast("d");
                    break;
                default:
                    break;
            }
        }


        return super.onOptionsItemSelected(item);
    }
    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

 
**Popup弹出菜单**

		
		
	// 点击一个 按钮时:
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.PopupMenu:
                //建立PopupMenu对象
                PopupMenu popup = new PopupMenu(MainActivity.this, v);
                //压入XML资源文件
                popup.getMenuInflater().inflate(R.menu.menu,popup.getMenu());
                //设置点击菜单选项事件
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_add:
                                toast("add");
                                break;
                            case R.id.delete:
                                toast("delete");
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
                //显示菜单
                popup.show();
                break;
            default:
                break;
        }
    }


**ActionMode 导航菜单**

      modeAction.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(actionMode != null) {
                    return false;
                }
                // 这里在start时， 也是可以得到 ActionMode的
                actionMode = startActionMode(new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                        menu.add(1, 1, 1, "hehe");
                        menu.add(1, 2, 1, "jjj");
                        menu.add(1, 3, 1, "ggg");
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                        switch (item.getItemId()) {
                            case 1:
                                toast("1");
                                break;
                            case 2:
                                toast("2");
                                break;
                            case 3:
                                toast("3");
                                break;
                            default:
                                break;
                        }
                        return false;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {
                        actionMode = null;
                    }
                });
                v.setSelected(true);        //设置为可选
                return true;
            }
        });


