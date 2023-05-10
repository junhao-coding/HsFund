import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/08  11:08
 */
@CloudApplication
public class ProductStarter {
    public static void main(String[] args) {
        CloudBootstrap.run(ProductStarter.class, args);
    }
}
