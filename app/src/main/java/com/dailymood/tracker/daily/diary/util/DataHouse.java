package com.dailymood.tracker.daily.diary.util;

import java.util.ArrayList;
import java.util.List;

public class DataHouse {

    public static List<String> getActivityList()
    {
        List<String> stringList = new ArrayList<>();

        for (int i = 1; i < 18; i++) {
            stringList.add("activity/a (" + i + ").png");
        }

        return stringList;
    }
}
