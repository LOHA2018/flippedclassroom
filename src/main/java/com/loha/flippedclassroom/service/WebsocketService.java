package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.TeamDao;
import com.loha.flippedclassroom.entity.Attendance;
import org.springframework.stereotype.Service;

/**
 * websocket service
 *
 * @author zhoujian
 * @date 2018/12/20
 */
@Service
public class WebsocketService {
    private final TeamDao teamDao;

    WebsocketService(TeamDao teamDao){
        this.teamDao=teamDao;
    }

    public void updateIsPreStatus(Attendance attendance) throws Exception{
        teamDao.updateIsPresentStatus(attendance);
    }


}
