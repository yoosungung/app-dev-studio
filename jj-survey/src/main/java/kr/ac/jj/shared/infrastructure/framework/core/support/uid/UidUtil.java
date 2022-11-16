package kr.ac.jj.shared.infrastructure.framework.core.support.uid;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import kr.ac.jj.shared.infrastructure.framework.core.support.sequence.BaseSequence;

public class UidUtil {

    private static final BaseSequence uidSeq = new BaseSequence(0, 999999, true);

    public static String generateUid() {
        return generateUid('0');
    }

    public static String generateUid(char type) {
        String[] hostAddresses;

        try {
            hostAddresses = InetAddress.getLocalHost().getHostAddress().split("\\.");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        Long timestamp = Long.parseLong(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"), 10);
        Long hostAddr = Long.parseLong(hostAddresses[2] + hostAddresses[3], 10);

        String timeStr = StringUtils.leftPad(Long.toString(timestamp, 36).toUpperCase(), 9, '0');
        String hostStr = StringUtils.leftPad(Long.toString(hostAddr, 36).toUpperCase(), 4, '0');
        String uidStr = StringUtils.leftPad(Long.toString(uidSeq.getNextValue(), 36).toUpperCase(), 4, '0');

        return timeStr + hostStr + uidStr + type;
    }

}
