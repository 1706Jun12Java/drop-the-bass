package main.java.com.revature.util;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;

public class S3Util
{
    private static final String BUCKET_NAME = "gigondemand";
    private static final AmazonS3 S3 = new AmazonS3Client(new ProfileCredentialsProvider());
    private static final String BASE_URL = "https://s3.amazonaws.com/";

    /**
     * Upload a file to Amazon S3 that saved at root bucket
     *
     * @param filename the name of the file
     * @param file The file itself
     */
    public static void uploadPicture(String filename, File file)
    {
        S3.putObject(new PutObjectRequest(BUCKET_NAME, filename, file) );

//        return BASE_URL + "/" + filename + "/" +file;
    }
}
