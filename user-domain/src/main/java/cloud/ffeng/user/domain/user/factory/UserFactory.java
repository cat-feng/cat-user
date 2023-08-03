package cloud.ffeng.user.domain.user.factory;

import cloud.ffeng.user.domain.base.component.SequenceGenerator;
import cloud.ffeng.user.domain.base.enums.SequenceType;
import cloud.ffeng.user.domain.platform.entity.PlatformUser;
import cloud.ffeng.user.domain.user.entity.User;

import static cloud.ffeng.user.common.util.IdCheckUtil.computeCheckSum;

/**
 * @author cat-feng
 */
public final class UserFactory {

    /**
     * 生成用户ID
     * <p>
     * 64位整型的最大值为：9223372036854775807
     * 用户ID生成取16位, 满足64位整型的限制，支持跨语言64位整类型。
     * 格式：[标识位2] + [保留位1] + [序列位11] + [库表位2] + [校验位1]
     * -对应：[88] + [固定位0] + [10位] + [00-99] + [0-9校验位]
     *
     * @return 用户ID
     */
    public static Long generateUserId() {
        long base = 88_0_0000000000_00_0L;
        // 散列位
        int sharding = 10 * SequenceGenerator.nextShardingSequence(SequenceType.USER_ID);

        // 生成10位的序列位
        long sequence = 1_00_0 * SequenceGenerator.nextUidSequence(SequenceType.USER_ID, sharding);

        // 拼接待生成的校验位的ID
        long waitCheckNum = base + sequence + sharding;

        // 计算校验位
        int checkSum = computeCheckSum(waitCheckNum);

        // 拼接用户ID
        return waitCheckNum + checkSum;
    }

    public static User createUser(PlatformUser platformUser) {
        return new User();
    }

}