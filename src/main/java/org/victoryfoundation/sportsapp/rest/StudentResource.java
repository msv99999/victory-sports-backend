package org.victoryfoundation.sportsapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.victoryfoundation.sportsapp.dao.StudentRepository;
import org.victoryfoundation.sportsapp.entity.Hub;
import org.victoryfoundation.sportsapp.entity.Sport;
import org.victoryfoundation.sportsapp.entity.Student;
import org.victoryfoundation.sportsapp.entity.StudentStatus;
import org.victoryfoundation.sportsapp.service.AmazonClient;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
public class StudentResource {

    @Autowired
    private AmazonClient amazonClient;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/student/hub/{hubId}")
    public List<Student> getAllStudentsByHubId(@PathVariable long hubId) {
        return studentRepository.findByHubId(hubId);
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable long id) {
        return studentRepository.findById(id).orElseGet(null);
    }

    @PutMapping("/student/{id}/family")
    public Student updateFamilyDetails(@PathVariable long id, Student studentRequest) {
        Student student = studentRepository.findById(id).get();
        if(student == null) return null;

        student.setFatherName(studentRequest.getFatherName());
        student.setFatherOccupation(studentRequest.getFatherOccupation());
        student.setFatherIncome(studentRequest.getFatherIncome());
        student.setMotherIncome(studentRequest.getMotherIncome());
        student.setMotherName(studentRequest.getMotherName());
        student.setMotherOccupation(studentRequest.getMotherOccupation());

        return studentRepository.save(student);
    }


    @PostMapping("/student")
    public ResponseEntity<Object> createStudentDetail(@RequestPart(value = "image1", required = false) MultipartFile image1, @RequestPart(value = "image2", required = false) MultipartFile image2,
                                                      @RequestPart(value = "aadharImage", required = false) MultipartFile aadharImage,
                                                      @RequestParam("hub_id") long hubId, @RequestParam("sport_id") long sportId,
                                                      @RequestParam("status") String status, @RequestParam("student_name") String studentName,
                                                      @RequestParam("active") Character active, @RequestParam("dob") Long dob,
                                                      @RequestParam(value = "aadhar") String aadhar, @RequestParam(value = "medical_record", required = false) String medicalRecord,
                                                      @RequestParam(value = "educational_institution", required = false) String educationalInstitution,
                                                      @RequestParam(value = "course", required = false) String course, @RequestParam(value = "year", required = false) Long year,
                                                      @RequestParam(value = "jersy_size", required = false) String jersySize, @RequestParam(value = "shoe_size", required = false) Long shoeSize,
                                                      @RequestParam(value = "weight") Long weight, @RequestParam(value = "address") String address,
                                                      @RequestParam(value = "land_mark", required = false) String landMark, @RequestParam(value = "father_name", required = false) String fatherName,
                                                      @RequestParam(value = "father_occupation", required = false) String fatherOccupation, @RequestParam(value = "father_income", required = false) Long fatherIncome,
                                                      @RequestParam(value = "mother_name", required = false) String motherName, @RequestParam(value = "mother_occupation", required = false) String motherOccupation,
                                                      @RequestParam(value = "mother_income", required = false) String motherIncome, @RequestParam(value = "siblings", required = false) Long siblings,
                                                      @RequestParam(value = "post_office", required = false) String postOffice, @RequestParam(value = "height") Double height) {

        Student student = new Student();
        student.setStudentName(studentName);
        student.setAadhar(aadhar);
        student.setWeight(weight);
        student.setSibblings(siblings);
        student.setShoeSize(shoeSize);
        student.setPostOffice(postOffice);
        student.setMotherOccupation(motherOccupation);
        student.setMotherName(motherName);
        student.setMotherIncome(motherIncome);
        student.setLandMark(landMark);
        student.setJersySize(jersySize);
        student.setHeight(height);
        student.setFatherOccupation(fatherOccupation);
        student.setFatherName(fatherName);
        student.setFatherIncome(fatherIncome);
        student.setEducationalInstitution(educationalInstitution);
        student.setMedicalRecord(medicalRecord);
        student.setCourse(course);

//        DateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");

        student.setDob(new Date(dob));
        student.setYear(year);


        student.setAddress(address);
        student.setActive(active);

        inputDataNormalization(student, hubId, sportId, status);

        try {
            StringBuilder studentImage = new StringBuilder();
            if (image1 != null) {
                studentImage.append(this.amazonClient.uploadFile(image1, "student", aadhar+"_1"));
                System.out.println("Uploaded image 1");
                studentImage.append("#");
            }
            if (image2 != null) {
                studentImage.append(this.amazonClient.uploadFile(image2, "student", aadhar+"_2"));
                System.out.println("Uploaded image 2");
            }
            student.setProfileImage(studentImage.toString());

            if (aadharImage != null) {
                student.setProfessionalImage(this.amazonClient.uploadFile(image2, "student", aadhar+"_3"));
                System.out.println("Uploaded professional Image");
            }
        } catch (Exception e) {
            return new ResponseEntity<>("There was an unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);

        }

        Student stud = studentRepository.save(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(stud.getStudentId()).toUri();
        return new ResponseEntity<>(ResponseEntity.created(location).build(), HttpStatus.CREATED);
    }

    private void inputDataNormalization(Student student, long hubId, long sportId, String status) {
        Hub hub = new Hub();
        hub.setHubId(hubId);

        Sport sport = new Sport();
        sport.setId(sportId);

        StudentStatus studentStatus = new StudentStatus();
        studentStatus.setStatus(status);

        student.setHubId(hub);
        student.setSportId(sport);
        student.setStatus(studentStatus);
    }
}
