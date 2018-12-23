package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Klass;
import com.loha.flippedclassroom.mapper.KlassMapper;
import com.loha.flippedclassroom.mapper.KlassStudentMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与班级相关的dao
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Repository
public class KlassDao {
    private final KlassMapper klassMapper;
    private final KlassStudentMapper klassStudentMapper;

    KlassDao(KlassMapper klassMapper,KlassStudentMapper klassStudentMapper){
        this.klassMapper=klassMapper;
        this.klassStudentMapper=klassStudentMapper;
    }


    /**
     * 根据课程id获取该课程下所有班级
     */
    public List<Klass> getKlassByCourseId(Long courseId) throws Exception{
        return klassMapper.selectKlassByCourseId(courseId);
    }

    /**
     * 根据id获取班级
     */
    public Klass getKlassById(Long klassId) throws Exception{
        return klassMapper.selectKlassById(klassId);
    }


    /**
     * 创建班级
     */
    public void createKlass(Klass klass) throws Exception{
       klassMapper.insertKlass(klass);
    }

    /**
     * 为了上传文件而查找当前班级的Id
     */
    public Long selectKlassId(Long courseId, Integer grade, Integer klassSerial) throws Exception{
        return klassMapper.selectKlassId(courseId,grade,klassSerial);
    }

    /**
     * 删除班级，级联删除班级学生
     */
    public void deleteKlassByKlassId(Long klassId) throws Exception{
        klassStudentMapper.deleteKlassStudentByKlassId(klassId);
        klassMapper.deleteKlassByKlassId(klassId);
    }

}
