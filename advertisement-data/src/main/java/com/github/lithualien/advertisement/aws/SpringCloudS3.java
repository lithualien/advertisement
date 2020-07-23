package com.github.lithualien.advertisement.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.github.lithualien.advertisement.config.AWSConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class SpringCloudS3 {

    private final AWSConfig amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public SpringCloudS3(AWSConfig amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void uploadFile(String fileName, MultipartFile multipartFile) {
        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(multipartFile.getContentType());
        data.setContentLength(multipartFile.getSize());

        try {
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucket, fileName, multipartFile.getInputStream(), data)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.amazonS3Client().putObject(putObjectRequest);
        } catch (IOException e) {

        }
    }

}
