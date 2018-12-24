package com.loha.flippedclassroom.mapper;
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
     * 添加或删除一个队伍中的某个同学，修改teamid的值即可
     * @param map 包含"courseId","studentId","teamId"
     * @throws Exception
     */
    void updateStudentStatusInTeam(Map<String,Long> map) throws Exception;
}
