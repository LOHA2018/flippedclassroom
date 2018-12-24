package com.loha.flippedclassroom.mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 与班级学生讨论课相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/17
 */
@Repository
public interface KlassStudentMapper {

    /**
     * 插入学生记录时，同时插入该学生所在班级课程
     * @param map 包含"klassId","teamId","courseId"
     * @throws Exception
     */
    void insertKlassStudent(Map<String,Long> map) throws Exception;

    /**
     * 判断某个学生在某门课程下是否组队
     * @param map 包含"courseId","studentId"
     * @return teamId,可能为空，为空则未组队
     * @throws Exception
     */
    Long selectTeamIdByStudentCourseId(Map<String,Long> map) throws Exception;

    /**
     * 添加队伍成员
     * @param map "studentId","teamId"
     * @throws Exception
     */
    void addMemberInTeam(Map<String,Long> map) throws Exception;

    /**
     * 删除队伍成员
     * @param map "studentId","teamId"
     * @throws Exception
     */
    void deleteMemberInTeam(Map<String,Long> map) throws Exception;





    /**
     * 插入班级学生记录前，查找班级学生数据是否存在
     * @param map 包含"klassId","teamId","courseId"
     * @throws Exception
     */
    Long selectKlassStudent(Map<String,Long> map) throws Exception;

    /**
     * 根据班级Id删除班级学生
     * @param klassId 班级Id
     * @return klassId
     * @throws Exception
     */
    void deleteKlassStudentByKlassId(@Param("klassId")Long klassId) throws Exception;

}
