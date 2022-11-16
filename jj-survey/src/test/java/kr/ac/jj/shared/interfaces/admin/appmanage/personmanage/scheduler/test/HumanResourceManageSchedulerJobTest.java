package kr.ac.jj.shared.interfaces.admin.appmanage.personmanage.scheduler.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.ac.jj.shared.interfaces.admin.appmanage.hrmanage.controller.HumanResourceManageController;

@SpringBootTest
public class HumanResourceManageSchedulerJobTest {

    private @Autowired HumanResourceManageController humanResourceManageController;

    @Test
    public void test() {
        humanResourceManageController.createOrUpdateAll();
    }

}
