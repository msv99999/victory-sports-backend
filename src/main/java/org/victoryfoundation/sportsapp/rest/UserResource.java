package org.victoryfoundation.sportsapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.victoryfoundation.sportsapp.dao.UserRepository;
import org.victoryfoundation.sportsapp.entity.Coach;
import org.victoryfoundation.sportsapp.entity.Sport;
import org.victoryfoundation.sportsapp.entity.Student;
import org.victoryfoundation.sportsapp.entity.User;
import org.victoryfoundation.sportsapp.entity.UserType;
import org.victoryfoundation.sportsapp.service.AmazonClient;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResource {

    @Autowired
    private AmazonClient amazonClient;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable long id) {

        Optional<User> user = Optional.of(userRepository.findById(id));
        if (!user.isPresent())
            throw new ResourceNotFoundException("id-" + id);
        return user.get();
    }

    @PutMapping("/users/{id}")
    public User updateUserDetails(@PathVariable long id, @RequestBody User user) {
        User oldUser = userRepository.findById(id);
        if(oldUser == null) return null;
        oldUser.setStatus(user.getStatus());
        oldUser.setUpdatedOn(Instant.now().toEpochMilli());
        return userRepository.save(oldUser);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {

        user.setCreatedOn(Instant.now().toEpochMilli());
        user.setUpdatedOn(Instant.now().toEpochMilli());
        User savedUser = userRepository.save(user);

        Optional<Coach> coach= Optional.ofNullable(savedUser.getCoach());
        if(coach.isPresent()){
            if(savedUser.getRole().getId()!=31){
                ResponseEntity<Object>  res= new ResponseEntity<>(
                        ResponseEntity.badRequest(),
                        HttpStatus.BAD_REQUEST);
            }
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        ResponseEntity<Object>  res= new ResponseEntity<>(
                ResponseEntity.created(location).build(),
                HttpStatus.CREATED);

        return res;
    }


    @PostMapping("/users/coach")
    public ResponseEntity<Object> createCoachWithMultiPart(@RequestPart(value = "id_proof", required = false) MultipartFile id_proof,
                                                           @RequestPart(value = "profile_image", required = false) MultipartFile profile_image,
                                                           @RequestParam("user_name") String username,
                                                           @RequestParam("name") String name,
                                                           @RequestParam("about") String about,
                                                           @RequestParam("sport_id") long sport_id,
                                                           @RequestParam("experience") String experience,
                                                           @RequestParam("education") String education,
                                                           @RequestParam("address") String address,
                                                           @RequestParam("zipcode") String zipcode,
                                                           @RequestParam("dob") long dob

    ){

        User user= new User();
        UserType role = new UserType();
        role.setId((long)31);
        user.setRole(role);
        user.setUsername(username);
        user.setName(name);

        Coach coach = new Coach();
        coach.setAddress(address);
        coach.setActive("A");
        coach.setEducation(education);
        Sport sport= new Sport();
        sport.setId(sport_id);
        coach.setSport(sport);
        coach.setAbout(about);
        coach.setExperience(experience);
        coach.setZipcode(zipcode);
        coach.setDob(dob);
        coach.setCreatedon(Instant.now().toEpochMilli());
        coach.setUpdated(Instant.now().toEpochMilli());
        coach.setName(name);

        user.setCoach(coach);
        User savedUser= userRepository.save(user);
        try {
            String id_proof_path = amazonClient.uploadFile(id_proof, "coach", savedUser.getCoach().getId().toString()+"id_proof");
            String profile_image_path = amazonClient.uploadFile(profile_image, "coach", savedUser.getCoach().getId().toString()+"profile_image");
            savedUser.getCoach().setImage(profile_image_path);
            savedUser.getCoach().setId_proof(id_proof_path);
            userRepository.save(savedUser);
        } catch(Exception e){
            userRepository.delete(savedUser);
            return new ResponseEntity<>(
                    ResponseEntity.badRequest(),
                    HttpStatus.BAD_REQUEST);
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        ResponseEntity<Object>  res= new ResponseEntity<>(
                ResponseEntity.created(location).build(),
                HttpStatus.CREATED);
        return res;

    }

    @GetMapping("/users/logon")
    public User findByUserId(@RequestParam("username") String id) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(id));
        if(!user.isPresent())
            throw new ResourceNotFoundException("logonId-" + id);
        return user.get();
    }


}
