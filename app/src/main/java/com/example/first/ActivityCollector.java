package com.example.first;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ActivityCollector
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/4/2 20:32
 * @Version: 1.0
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for(Activity activity: activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
