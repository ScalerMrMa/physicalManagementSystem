package com.five;

import com.five.domain.InnerUser;
import com.five.domain.LoginUser;
import com.five.service.OrdinaryUserService;
import com.five.vo.DataVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhysicalManagementSystemApplicationTests {
    InnerUser innerUser = new InnerUser();

    @Autowired
    private OrdinaryUserService userService;

    @Test
    public void testGetNormalUsers() {
        DataVo<LoginUser> normalUsers = userService.getNormalUsers("");
        System.out.println(normalUsers);
    }


    @Test
    public void test () {

    }
}
