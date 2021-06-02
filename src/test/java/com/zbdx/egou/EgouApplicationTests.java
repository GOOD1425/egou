package com.zbdx.egou;

import com.zbdx.egou.utils.IPutils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class EgouApplicationTests {

    @Test
    void contextLoads() throws IOException {
        Md5Hash md5Hash = new Md5Hash("aa123123");
        System.out.println(md5Hash);

    }

}