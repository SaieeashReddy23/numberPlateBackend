package com.sai.numberPlate.controller;

import com.amazonaws.services.rekognition.model.TextDetection;
import com.sai.numberPlate.Entity.UniqIdEntity;
import com.sai.numberPlate.modals.Registered;
import com.sai.numberPlate.modals.Student;
import com.sai.numberPlate.modals.Text;
import com.sai.numberPlate.modals.VehicleAllDetails;
import com.sai.numberPlate.repository.UniqIdRepo;
import com.sai.numberPlate.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class StorageController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private UniqIdRepo repo;

//    @RequestParam("file") MultipartFile file,
    @RequestMapping(value = "/upload/file", headers = ("content-type=multipart/*"), method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<TextDetection>> upload(@RequestPart("file") MultipartFile file){

        log.error("Entered into the upload method");
        return new ResponseEntity<>(storageService.uploadFile(file), HttpStatus.OK);
    }


    @RequestMapping(value = "/upload/text")
    public ResponseEntity<String> uploadText(@RequestBody VehicleAllDetails vehicleAllDetails){
        log.error("entered the upload text nethod");
        System.out.println(vehicleAllDetails);

        return new ResponseEntity<>(storageService.uploadText(vehicleAllDetails),HttpStatus.OK);
    }


    @GetMapping("/check")
    public ResponseEntity<Student> checkApi(){
        return new ResponseEntity<>(new Student("Saieeash","Murali","Rama"),HttpStatus.OK);
    }

    @PostMapping("/upload/id")
    public String uploadId(@RequestBody UniqIdEntity uniqIdEntity){
        log.error("Entered the uniqid uploading method");
        System.out.println(uniqIdEntity);
        UniqIdEntity uniqId = repo.findByDeviceId(uniqIdEntity.getDeviceId());
        System.out.println(uniqId);
        if(uniqId == null){
            repo.save(uniqIdEntity);
        }

        return "Uploades successfully";
    }

    @PostMapping("/delete/id")
    public String deleteId(@RequestBody UniqIdEntity uniqIdEntity){
        log.error("Entered the uniqid deleting method");
        System.out.println(uniqIdEntity);
        UniqIdEntity uniqId = repo.findByDeviceId(uniqIdEntity.getDeviceId());
        System.out.println(uniqId);
        repo.delete(uniqId);
        return "deleted successfully successfully";
    }

    @PostMapping("/check/id")
    public ResponseEntity<Registered> checkId(@RequestBody UniqIdEntity uniqIdEntity){
        log.error("Entered the uniqid checking method");
        System.out.println(uniqIdEntity);
        UniqIdEntity uniqId = repo.findByDeviceId(uniqIdEntity.getDeviceId());
        System.out.println(uniqId);
        if(uniqId == null){
            return new ResponseEntity<>(new Registered(uniqIdEntity.getDeviceId(), false),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Registered(uniqIdEntity.getDeviceId(), true),HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String fileName){

        byte[]  byteArray =  storageService.downloadFile(fileName);
        ByteArrayResource byteArrayResource = new ByteArrayResource(byteArray);

        return ResponseEntity
                .ok().
                contentLength(byteArray.length)
                .header("Content-type","application/octet-stream")
                .header("Content-disposition","attachment; filename=\"" + fileName + "\"")
                .body(byteArrayResource);
    }
    @GetMapping("/delete/{fileName}")
    public ResponseEntity<String> delete(@PathVariable String fileName){
        return new ResponseEntity<>(storageService.deleteFile(fileName),HttpStatus.OK);

    }


}
