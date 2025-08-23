import com.dqv5.soccer.pojo.SysMenu;
import com.dqv5.soccer.pojo.SysUser;
import com.dqv5.soccer.structmapper.SysMenuStructMapper;
import com.dqv5.soccer.structmapper.SysUserStructMapper;
import com.dqv5.soccer.table.SysMenuTable;
import com.dqv5.soccer.table.SysUserTable;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * @author duqian
 * @date 2025/8/23
 */
public class TestMapStruct {
    @Test
    public void testUser() {
        SysUserTable table = new SysUserTable();
        table.setUserId(UUID.randomUUID().toString());
        table.setNickName("test");
        table.setPassword("123456");
        table.setGender(1);
        table.setEmail("test123@demo.edu.cn");
        table.setPhone("13800138000");
        table.setStatus(1);
        SysUser pojo = SysUserStructMapper.INSTANCE.entityToPojo(table);
        System.out.println(pojo);
    }

    @Test
    public void testMenu() {
        SysMenuTable table = new SysMenuTable();
        table.setHide(0);
        table.setHideChildren(1);
        SysMenu pojo = SysMenuStructMapper.INSTANCE.entityToPojo(table);
        System.out.println(pojo);
    }
}
