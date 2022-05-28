package helper;

import com.ddf.group.purchase.core.helper.CommonHelper;
import com.ddf.group.purchase.core.model.request.common.SendSmsCodeRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/15 23:21
 */
@SpringBootTest/*(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)*/
@RunWith(SpringJUnit4ClassRunner.class)
public class CommonHelperTest {

    @Autowired
    private CommonHelper commonHelper;

    @Test
    public void testSmsCode() {
        final SendSmsCodeRequest request = new SendSmsCodeRequest();
        request.setMobile("18356784598");
        commonHelper.sendSmsCode(request);
    }
}
