import martinez.james.StringUtils;
import org.junit.Test;

/**
 * Created by james on 4/9/16.
 */
public class StringUtilsTester {

    /**
     * Just a dumb sanity check
     */
    @Test
    public void basicCheck() {
        assert "foofoofoo".equals(StringUtils.repeat("foo", 3));
        assert "".equals(StringUtils.repeat("foo", 0));
        assert "".equals(StringUtils.repeat("foo", -1));
    }
}
