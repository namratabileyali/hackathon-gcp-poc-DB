package com.hackathon.poc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.api.client.util.IOUtils;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@SpringBootApplication
public class PocApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocApplication.class, args);
		 //bucket();
		
	}

	private static void bucket() {

		String PROJECT_ID = "indigo-anchor-392208";
		String PATH_TO_JSON_KEY = "D:\\cert.json";
		String BUCKET_NAME = "hackathon-poc";
		String OBJECT_NAME = "cert.json";

		StorageOptions options;
		try {
			options = StorageOptions.newBuilder().setProjectId(PROJECT_ID)
					.setCredentials(GoogleCredentials.fromStream(new FileInputStream(PATH_TO_JSON_KEY))).build();
			System.out.println(GoogleCredentials.fromStream(new FileInputStream(PATH_TO_JSON_KEY)));
			Storage storage = options.getService();
			//Bucket bucket = storage.create(BucketInfo.of("baeldung-bucket"));
			Blob blob = (Blob) storage.get(BUCKET_NAME, OBJECT_NAME);
			//blob.downloadTo(Paths.get("D:\\new.json"));;
			ReadChannel reader;
	        String result = null;
	        if (blob != null) {
	            reader = blob.reader();
	            InputStream inputStream = Channels.newInputStream(reader);
	           result = org.apache.commons.io.IOUtils.toString(inputStream, "UTF-8");
	        }
	        System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ReadChannel r = blob.reader();

	}

}
