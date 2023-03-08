package com.dqv5.soccer.utils;

import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.lang.reflect.Method;

@Component
@Slf4j
public class IPUtils {
    private static String dbPath;

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        dbPath = env.getProperty("soccer.ip2region.db-path");
        log.debug("ip2region.db file path: {}", dbPath);
    }

    public static String getIpRegionInfo(String ip) {
        File file = new File(dbPath);
        if (!file.exists()) {
            log.error("Invalid ip2region.db file: {}", dbPath);
            return null;
        }

        //查询算法
        int algorithm = DbSearcher.BTREE_ALGORITHM; //B-tree
        //DbSearcher.BINARY_ALGORITHM //Binary
        //DbSearcher.MEMORY_ALGORITYM //Memory
        try {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, dbPath);

            //define the method
            Method method = null;
            switch (algorithm) {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }

            DataBlock dataBlock = null;
            if (!Util.isIpAddress(ip)) {
                log.error("Invalid ip address: {}", ip);
            }
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            String regionInfo = dataBlock.getRegion();
            String[] splitIpString = regionInfo.split("\\|");
            if (!"0".equals(splitIpString[1])) {
                return regionInfo;
            }
            if (splitIpString[2].equals(splitIpString[3])) {
                return splitIpString[2];
            }
            return splitIpString[2] + "-" + splitIpString[3];
        } catch (Exception e) {
            log.error("IPUtils Exception: {}", e.getMessage());
        }
        return null;
    }
}
