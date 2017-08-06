package main.java.com.revature.util;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Component
public class S3Util
{
    private static final String BUCKET_NAME = "gigondemand";
    private static final AmazonS3 S3 = new AmazonS3Client(new ProfileCredentialsProvider());
    private static final String PICTURE_LOCATION = "/pictures/";

    /**
     * Upload image to Amazon S3 service
     *
     * @param file     The file to be uploaded. The name of the file will be used for saving.
     * @param location the directory where to save the file (I.E username/pictures/file's-name)
     * @return The url where the file was saved
     */
    public static String uploadPicture(MultipartFile file, String location)
    {
        String saveLocation = location + PICTURE_LOCATION + file.getOriginalFilename();

        try
        {
            InputStream inputStream = file.getInputStream();
            File f = new File(file.getOriginalFilename());
            file.transferTo(f);
            S3.putObject(new PutObjectRequest(BUCKET_NAME, saveLocation, f).withCannedAcl(CannedAccessControlList.PublicRead));

            inputStream.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return S3.getUrl(BUCKET_NAME, saveLocation).toString();
    }
}
