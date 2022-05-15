package com.sai.numberPlate.service;


import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.sai.numberPlate.Entity.VehicleDetails;
import com.sai.numberPlate.modals.VehicleAllDetails;
import com.sai.numberPlate.repository.VehicleDetailsRepo;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private AmazonRekognition rekognition;

    @Autowired
    private VehicleDetailsRepo repo;


    public List<TextDetection> uploadFile(MultipartFile file){

        log.error("Entered into the upload file method");

        String fileName = "images/"+ System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File fileObj = multipartToFile(file);
        s3Client.putObject(bucketName,fileName,fileObj);
        fileObj.delete();

        return  detectText(fileName);
    }

    public String uploadText(VehicleAllDetails vehicleAllDetails){
        log.error(("Entered the upload text method"));
        String fileName = "VehicleNumber/" +  System.currentTimeMillis();
        repo.save(new VehicleDetails(vehicleAllDetails.getText(), vehicleAllDetails.getLatitude(), vehicleAllDetails.getLongitude(),vehicleAllDetails.getTimestamp()));
//        s3Client.putObject(bucketName,fileName,text);
        return "uploaded successful";
    }

    public List<TextDetection> detectText(String photo){

        String bucket = "saieeash-aws-bucket";


//        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

//        DetectLabelsRequest request = new DetectLabelsRequest()
//                .withImage(new Image()
//                        .withS3Object(new com.amazonaws.services.rekognition.model.S3Object()
//                                .withName(photo).withBucket(bucket)))
//                .withMaxLabels(10)
//                .withMinConfidence(75F);
//
//        try {
//            DetectLabelsResult result = rekognition.detectLabels(request);
////            DetectLabelsResult result = rekognitionClient.detectLabels(request);
//            List<Label> labels = result.getLabels();
//
//            System.out.println("Detected labels for " + photo);
//            for (Label label: labels) {
//                System.out.println(label.getName() + ": " + label.getConfidence().toString());
//            }
//        } catch(AmazonRekognitionException e) {
//            e.printStackTrace();
//        }


        DetectTextRequest textRequest = new DetectTextRequest()
                .withImage(new Image()
                        .withS3Object(new com.amazonaws.services.rekognition.model.S3Object()
                                .withName(photo)
                                .withBucket(bucket)));




        try {
            DetectTextResult result = rekognition.detectText(textRequest);
            List<TextDetection> textDetections = result.getTextDetections();

            System.out.println("Detected lines and words for " + photo);
            for (TextDetection text: textDetections) {

                System.out.println("Detected: " + text.getDetectedText());
                System.out.println("Confidence: " + text.getConfidence().toString());
                System.out.println("Id : " + text.getId());
                System.out.println("Parent Id: " + text.getParentId());
                System.out.println("Type: " + text.getType());
                System.out.println();
            }
            return textDetections;
        } catch(AmazonRekognitionException e) {
            e.printStackTrace();
        }

        return null;
    }


    public byte[] downloadFile(String fileName){
        S3Object s3Object =  s3Client.getObject(bucketName,fileName);
        InputStream inputStream = s3Object.getObjectContent();

        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName){
        s3Client.deleteBucket(fileName);

        return fileName + "removed ";
    }

    private File multipartToFile(MultipartFile file){
        File convertedFile = new File((file.getOriginalFilename()));

        try {
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(file.getBytes());

        }catch (Exception e){
            log.error("Error converting multipart file to file");
        }

        return convertedFile;
    }
}
