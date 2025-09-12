package org.example.config;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class OssConfig {

    @Value("${alioss.endpoint}")
    private String endpoint;

    @Value("${alioss.access-key-id}")
    private String accessKeyId;

    @Value("${alioss.access-key-secret}")
    private String accessKeySecret;

    @Value("${alioss.bucket-name}")
    private String bucketName;

    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    public String getBucketName() {
        return bucketName;
    }
}
