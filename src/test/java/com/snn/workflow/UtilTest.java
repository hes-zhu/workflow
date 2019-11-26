package com.snn.workflow;

import com.snn.workflow.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @className Md5Test
 * @Author SNN
 * @Date 2019/9/22 0:29
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTest {

    @Test
    public void demo() {
        String ps = MD5Util.MD5EncodeUtf8("123456");
        System.out.println(ps);
    }

    @Test
    public void testUuid() {
        String forgetToken = UUID.randomUUID().toString();
        System.out.println(forgetToken);
    }

    @Test
    public void cc() {
        byte[] a = null;
        if (a == null) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    @Test
    public void as() {
        String resource = System.getProperty("user.dir");
        System.out.println(resource);
    }
}
