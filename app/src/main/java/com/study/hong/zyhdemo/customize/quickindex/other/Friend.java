package com.study.hong.zyhdemo.customize.quickindex.other;

import android.support.annotation.NonNull;

import com.study.hong.zyhdemo.customize.utils.Utils;


/**
 * Created by 洪 on 2019/5/9.
 */

public class Friend implements Comparable<Friend> {
    private String name;
    private String pinyin;

    public Friend(String name) {
        this.name = name;
        //一开始就转化好拼音
        setPinyin(Utils.getPinyin(name));

    }

    public String getName() {
        return name;
    }
    public String getPinyin() {
        return pinyin;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(@NonNull Friend friend) {
        return getPinyin().compareTo(friend.getPinyin());
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
