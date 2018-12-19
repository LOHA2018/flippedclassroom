package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.entity.Attendance;
import com.loha.flippedclassroom.entity.KlassSeminar;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Author: birden
 * @Description:
 * @Date:20:03 2018/12/19
 */
@Service
public class KlassSeminarService {



    public List<KlassSeminar> getKlassSeminar(long klassId, long seminarId){}

    public List<Attendance> getAttendance(long klassSeminarId){}


}
