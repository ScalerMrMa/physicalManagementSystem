package com.five;

import com.five.domain.OrdinaryUser;
import com.five.service.OrdinaryUserService;
import com.five.vo.DataVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhysicalManagementSystemApplicationTests {

    @Autowired
    private OrdinaryUserService userService;

    @Test
    public void testGetNormalUsers() {
        DataVo<OrdinaryUser> normalUsers = userService.getNormalUsers("");
        System.out.println(normalUsers);
    }

    @Test
    public void test () {
        userService.test();
    }
}
