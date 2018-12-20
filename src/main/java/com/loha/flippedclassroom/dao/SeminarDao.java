package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Seminar;
import com.loha.flippedclassroom.mapper.SeminarMapper;
import com.loha.flippedclassroom.pojo.DTO.SeminarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SeminarDao {
    @Autowired
    SeminarMapper seminarMapper;
/**
 * @Author: birden
 * @Description:创建讨论课,异常处理
 * @Date:12:42 2018/12/20
 */
    public void createSeminar(Seminar seminar) throws Exception{
        if (null!=seminarMapper.selectSeminarById(seminar.getId()))
        {
            throw new Exception();
        }
        seminarMapper.createSeminar(seminar);
    }
/**
 * @Author: birden
 * @Description: 更新讨论课 异常处理
 * @Date: 2018/12/20 13:36
 */
    public void updateSeminar(Seminar seminar)throws Exception{
        if (null==seminarMapper.selectSeminarById(seminar.getId()))
        {
            throw new Exception();
        }
        seminarMapper.updateSeminar(seminar);
    }
    /**
     * @Author: birden
     * @Description:删除讨论课 异常处理
     * @Date:13:14 2018/12/20
     */
    public void deleteSeminar(long seminarId)throws Exception{
        if (null==seminarMapper.selectSeminarById(seminarId))
        {
            throw new Exception();
        }
        seminarMapper.deleteSeminar(seminarId);
    }
}
