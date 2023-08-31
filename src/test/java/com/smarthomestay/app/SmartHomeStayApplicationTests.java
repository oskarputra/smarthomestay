package com.smarthomestay.app;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmartHomeStayApplicationTests {

    @Test
    void contextLoads() {
        int input = 10;

        if(input > 0){
            assert true;
        }else{
            assert false;
        }
        
    }

    //unit test + mock

}
