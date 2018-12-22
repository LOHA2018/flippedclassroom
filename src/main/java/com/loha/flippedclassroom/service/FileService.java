package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.SeminarDao;
import com.loha.flippedclassroom.dao.StudentDao;
import com.loha.flippedclassroom.dao.TeamDao;
import com.loha.flippedclassroom.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * file service
 *
 * @author zhoujian
 * @date 2018/12/20
 */
@Service
@Slf4j
public class FileService {

    private final TeamDao teamDao;
    private final SeminarDao seminarDao;
    private final StudentDao studentDao;

    FileService(TeamDao teamDao, SeminarDao seminarDao,StudentDao studentDao){
        this.teamDao=teamDao;
        this.seminarDao=seminarDao;
        this.studentDao=studentDao;
    }

    /**
     * 组员上传PPT
     */
    public void teamSubmitPowerPoint(MultipartFile file,Long teamId,Long klassId,Long seminarId) throws Exception{
        String filename=file.getOriginalFilename();
        String filepath="C:\\Users\\Administrator\\Desktop\\test\\"+klassId+"\\"+teamId+"\\";

        File dest=new File(filepath+filename);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        //需要try
        file.transferTo(dest);

        Long klassSeminarId=seminarDao.getKlassSeminar(klassId,seminarId).getId();
        teamDao.submitPowerPoint(klassSeminarId,teamId,filename,filepath);
    }

    /**
     * 教师上传学生名单
     */
    public void uploadStudentList(MultipartFile file,Long klassId) throws Exception{
        String filename=file.getOriginalFilename();
        boolean isExcel2003=true;
        List<Student> students=new ArrayList<>();
        if(filename.matches("^.+\\.(?i)(xlsx)$")){
            isExcel2003=false;
        }

        InputStream input=file.getInputStream();
        Workbook wb=null;
        if(isExcel2003){
            wb=new HSSFWorkbook(input);
        }
        else {
            wb=new XSSFWorkbook(input);
        }

        Sheet sheet=wb.getSheetAt(0);

        Student temp;
        for(int r=1;r<=sheet.getLastRowNum();r++){
            Row row=sheet.getRow(r);
            if(row==null){
                continue;
            }

            temp=new Student();

            String account=row.getCell(0).getStringCellValue();
            String studentName=row.getCell(1).getStringCellValue();

            temp.setAccount(account);
            temp.setStudentName(studentName);

            students.add(temp);
        }

        for(Student student:students){
            studentDao.insertStudent(student);
            studentDao.insertKlassStudent(klassId,student.getAccount());
        }
    }
}
