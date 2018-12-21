package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.SeminarDao;
import com.loha.flippedclassroom.dao.StudentDao;
import com.loha.flippedclassroom.dao.TeamDao;
import com.loha.flippedclassroom.entity.Attendance;
import com.loha.flippedclassroom.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    private final StudentDao studentDao;

    FileService(TeamDao teamDao, SeminarDao seminarDao,StudentDao studentDao){
        this.teamDao=teamDao;
        this.studentDao=studentDao;
    }

    private void saveFile(MultipartFile file,String filepath,String filename) throws Exception{
        File dest=new File(filepath+filename);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        //需要try
        file.transferTo(dest);
    }

    /**
     * 组员上传PPT
     */
    public void teamSubmitPowerPoint(MultipartFile file,Long attendanceId) throws Exception{
        String filename=file.getOriginalFilename();
        String filepath="C:\\Users\\Administrator\\Desktop\\PPT\\"+attendanceId+"\\";

        saveFile(file,filepath,filename);

        teamDao.submitPowerPoint(attendanceId,filename,filepath);
    }

    /**
     * 组员下载PPT
     */
    public void teamDownloadPowerPoint(HttpServletResponse response, Long attendanceId) throws Exception {
        Attendance attendance = teamDao.getAttendanceById(attendanceId);
        String filename = attendance.getPptName();
        String filepath = attendance.getPptUrl();
        log.info(filepath+filename);
        File file = new File(filepath, filename);
        if (file.exists()) {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String( filename.getBytes("gb2312"), "ISO8859-1" ));
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;

            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 组员上传报告
     */
    public void teamSubmitReport(MultipartFile file,Long attendanceId) throws Exception{
        String filename=file.getOriginalFilename();
        String filepath="C:\\Users\\Administrator\\Desktop\\report\\"+attendanceId+"\\";

        saveFile(file,filepath,filename);

        teamDao.submitReport(attendanceId,filename,filepath);
    }

    /**
     * 教师上传学生名单
     */
    public void uploadStudentList(MultipartFile file,Integer klassId) throws Exception{
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
        }
    }
}
