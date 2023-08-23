package cloud.ffeng.user.infra.dal.dao;

import cloud.ffeng.user.infra.dal.dataobj.UserLoginDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author cat-feng
 */
@Mapper
public interface UserLoginDAO {

    Map<String, Object> select();

    int countById(Long loginFlowId);
}
