package com.sai.numberPlate.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreConfig {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;


    @Bean
    public AmazonS3 s3Client(){
        AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);

        return AmazonS3ClientBuilder
                .standard().
                withCredentials(new AWSStaticCredentialsProvider(credentials)).
                withRegion(region).build();
    }

    @Bean
    public AmazonRekognition amazonRekognition(){
        AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);

        return AmazonRekognitionClientBuilder
                .standard().
                withCredentials(new AWSStaticCredentialsProvider(credentials)).
                withRegion(region).build();
    }

}
