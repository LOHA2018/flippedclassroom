package com.loha.flippedclassroom.config.security;


import com.loha.flippedclassroom.entity.Student;
import com.loha.flippedclassroom.entity.Teacher;
import com.loha.flippedclassroom.mapper.StudentMapper;
import com.loha.flippedclassroom.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * service
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsServiceImpl(StudentMapper studentMapper, TeacherMapper teacherMapper, PasswordEncoder passwordEncoder){
        this.studentMapper=studentMapper;
        this.teacherMapper=teacherMapper;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        List<GrantedAuthority> roleList = new LinkedList<>();
        Teacher teacher = teacherMapper.selectTeacherByNum(username);
        if (teacher!=null) {
            roleList.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
            String password = passwordEncoder.encode(teacher.getPassword());
            return new User(username, password, roleList);
        }
        Student student = studentMapper.selectStudentByNum(username);
        if (student!=null) {
            roleList.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
            String password = passwordEncoder.encode(student.getPassword());
            return new User(username, password, roleList);
        }
        throw new UsernameNotFoundException("User " + username + " not found!");
    }
}
