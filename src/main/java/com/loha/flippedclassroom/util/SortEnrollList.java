package com.loha.flippedclassroom.util;

import com.loha.flippedclassroom.entity.Attendance;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对报名队列进行排序
 *
 * @author zhoujian
 * @date 2018/12/19
 */
public class SortEnrollList {

    private static List<Integer> orderList(List<Attendance> attendances){
        List<Integer> list=new LinkedList<>();
        for(Attendance attendance:attendances){
            list.add(attendance.getTeamOrder());
        }
        return list;
    }

    public static List<Attendance> sort(List<Attendance> list,Integer teamLimit){
        List<Attendance> attendances=list.stream()
                                        .sorted((u1,u2)->u1.getTeamOrder().compareTo(u2.getTeamOrder()))
                                        .collect(Collectors.toList());
        List<Integer> orderList=orderList(attendances);
        for(int i=1;i<=teamLimit;i++){
            if(!orderList.contains(i)){
                Attendance temp=new Attendance();
                temp.setTeamOrder(i);
                attendances.add(i-1,temp);
            }
        }
        return attendances;
    }
}
