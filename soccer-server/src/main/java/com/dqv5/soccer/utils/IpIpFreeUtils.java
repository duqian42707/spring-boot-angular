package com.dqv5.soccer.utils;

import net.ipip.ipdb.City;
import net.ipip.ipdb.IPFormatException;
import net.ipip.ipdb.InvalidDatabaseException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

/**
 * @author duqian
 * @date 2019/9/9
 */
public class IpIpFreeUtils {

    private static City cityDb;

    static {
        InputStream in = null;
        try {
            ClassPathResource path = new ClassPathResource("/files/ipipfree.ipdb");
            in = path.getInputStream();
            cityDb = new City(in);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public static String getAddressFromIp(String ip) {
        StringBuilder address = new StringBuilder();
        try {
            if (ip.contains(":")) {
                return "";
            }
            String[] cns = cityDb.find(ip, "CN");
            for (String cn : cns) {
                if (StringUtils.isNotBlank(cn)) {
                    address.append("-").append(cn);
                }
            }
            if (address.length() > 1) {
                return address.substring(1);
            }
        } catch (IPFormatException | InvalidDatabaseException e) {
            e.printStackTrace();
        }
        return address.toString();
    }
}
