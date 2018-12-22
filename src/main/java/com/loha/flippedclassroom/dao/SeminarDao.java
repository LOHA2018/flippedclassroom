package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.KlassSeminar;
import com.loha.flippedclassroom.entity.Seminar;
import com.loha.flippedclassroom.mapper.AttendanceMapper;
import com.loha.flippedclassroom.mapper.KlassSeminarMapper;
import com.loha.flippedclassroom.mapper.SeminarMapper;
import com.loha.flippedclassroom.pojo.DTO.SeminarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与讨论课相关的dao
 *
 * @author zhoujian
 * @date 2018/12/18
 */
@Repository
public class SeminarDao {

    private final KlassSeminarMapper klassSeminarMapper;
    private final SeminarMapper seminarMapper;

    SeminarDao(KlassSeminarMapper klassSeminarMapper, SeminarMapper seminarMapper) {
        this.klassSeminarMapper = klassSeminarMapper;
        this.seminarMapper = seminarMapper;
    }

//    /**
//     * 获取KlassSeminar对象，用于后续获取KlassSeminar的id
//     */
//    public KlassSeminar getKlassSeminar(Long klassId, Long seminarId) throws Exception {
//        KlassSeminar klassSeminar = new KlassSeminar();
//        klassSeminar.setKlassId(klassId);
//        klassSeminar.setSeminarId(seminarId);
//        return klassSeminarMapper.selectKlassSeminarByKlassSeminarId(klassSeminar);
//    }

    /**
     * @Author: birden
     * @Description: 用参数拿，不包成对象
     * @Date: 2018/12/21 18:57
     */
    public KlassSeminar getKlassSeminar(Long klassId, Long seminarId) throws Exception {
        KlassSeminar klassSeminar = new KlassSeminar();
        return klassSeminarMapper.selectKlassSeminar(klassId,seminarId);
    }
    /**
     * 根据Id获取当前的讨论课
     */
    public Seminar getCurSeminar(Long seminarId) throws Exception {
        return seminarMapper.selectSeminarById(seminarId);
    }

    /**
     * @Author: birden
     * @Description:创建讨论课,异常处理
     * @Date:12:42 2018/12/20
     */
    public void createSeminar(Seminar seminar) throws Exception {
        if (null != seminarMapper.selectSeminarById(seminar.getId())) {
            throw new Exception();
        }
        seminarMapper.createSeminar(seminar);
    }

    /**
     * @Author: birden
     * @Description: 更新讨论课 异常处理
     * @Date: 2018/12/20 13:36
     */
    public void updateSeminar(Seminar seminar) throws Exception {
        if (null == seminarMapper.selectSeminarById(seminar.getId())) {
            throw new Exception();
        }
        seminarMapper.updateSeminar(seminar);
    }

    /**
     * @Author: birden
     * @Description:删除讨论课 异常处理
     * @Date:13:14 2018/12/20
     */
    public void deleteSeminar(long seminarId) throws Exception {
        if (null == seminarMapper.selectSeminarById(seminarId)) {
            throw new Exception();
        }
        seminarMapper.deleteSeminar(seminarId);
    }
}
