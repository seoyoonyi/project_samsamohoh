package com.server.aws;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Component
public class S3ImageFileUploader {

	@Autowired
	private AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	public String uplode(MultipartFile file) throws IOException {
		String fileName = getUuid() + file.getOriginalFilename();
		amazonS3Client.putObject(new PutObjectRequest(bucket,fileName,file.getInputStream(),null).withCannedAcl(CannedAccessControlList.PublicRead));
		return amazonS3Client.getUrl(bucket, fileName).toString();
	}
	
	public void deleteFile(String imageName) {
		amazonS3Client.deleteObject(bucket,imageName);
	}

	private static String getUuid() {
		return UUID.randomUUID().toString();
	}
}
