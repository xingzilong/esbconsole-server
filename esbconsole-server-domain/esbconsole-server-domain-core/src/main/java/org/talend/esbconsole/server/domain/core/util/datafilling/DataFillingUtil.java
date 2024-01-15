package org.talend.esbconsole.server.domain.core.util.datafilling;

import org.talend.esbconsole.server.domain.api.model.APICallTotalAnalysisDTO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

/**
 * 数据填充工具类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class DataFillingUtil {

    public static void filling(List<APICallTotalAnalysisDTO> sourceDataList, LocalDate startDate, LocalDate endDate) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        // 补全空白日期数据
        for (long i = 0; i < days; i++) {
            LocalDate currentDate = startDate.plus(i, ChronoUnit.DAYS);
            boolean found = false;
            for (APICallTotalAnalysisDTO dto : sourceDataList) {
                if (dto.getDate().equals(currentDate)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                sourceDataList.add(new APICallTotalAnalysisDTO(currentDate, 0L));
            }
        }

        // 按照时间升序排序
        Collections.sort(sourceDataList, (dto1, dto2) -> dto1.getDate().compareTo(dto2.getDate()));
    }


    public static void filling410000(List<APICallTotalAnalysisDTO> sourceDataList, LocalDate startDate, LocalDate endDate) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        // 补全空白日期数据
        for (long i = 0; i < days; i++) {
            LocalDate currentDate = startDate.plus(i, ChronoUnit.DAYS);
            boolean found = false;
            for (APICallTotalAnalysisDTO dto : sourceDataList) {
                if (dto.getDate().equals(currentDate)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                sourceDataList.add(new APICallTotalAnalysisDTO(currentDate, 0L));
            }
        }

        // 按照时间升序排序
        Collections.sort(sourceDataList, (dto1, dto2) -> dto1.getDate().compareTo(dto2.getDate()));

        String unit = getUnit4Count(sourceDataList);

        // 转换单位
        for (APICallTotalAnalysisDTO dto : sourceDataList) {
            double convertedCount;
            if (unit.equals("万（次）")) {
                convertedCount = (double) dto.getTotal() / 10000;
            } else if (unit.equals("亿（次）")) {
                convertedCount = (double) dto.getTotal() / (10000 * 10000);
            } else {
                convertedCount = dto.getTotal();
            }
            dto.setBestData(convertedCount);
            dto.setUnit(unit);
        }
    }


    public static void filling41024(List<APICallTotalAnalysisDTO> sourceDataList, LocalDate startDate, LocalDate endDate) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        // 补全空白日期数据
        for (long i = 0; i < days; i++) {
            LocalDate currentDate = startDate.plus(i, ChronoUnit.DAYS);
            boolean found = false;
            for (APICallTotalAnalysisDTO dto : sourceDataList) {
                if (dto.getDate().equals(currentDate)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                sourceDataList.add(new APICallTotalAnalysisDTO(currentDate, 0L));
            }
        }

        // 按照时间升序排序
        Collections.sort(sourceDataList, (dto1, dto2) -> dto1.getDate().compareTo(dto2.getDate()));

        String unit = getUnit4Metering(sourceDataList);

        // 转换单位
        for (APICallTotalAnalysisDTO dto : sourceDataList) {
            double convertedCount;
            if (unit.equals("KB")) {
                convertedCount = (double) dto.getTotal() / 1024;
            } else if (unit.equals("MB")) {
                convertedCount = (double) dto.getTotal() / (1024 * 1024);
            } else if (unit.equals("GB")) {
                convertedCount = (double) dto.getTotal() / (1024 * 1024 * 1024);
            } else {
                convertedCount = dto.getTotal();
            }
            dto.setBestData(convertedCount);
            dto.setUnit(unit);
        }
    }

    /**
     * 设置单位（计数单位-次数）
     *
     * @param sourceDataList
     * @return
     */
    private static String getUnit4Count(List<APICallTotalAnalysisDTO> sourceDataList) {
        String unit = null;
        long maxTotal = 0;
        for (APICallTotalAnalysisDTO dto : sourceDataList) {
            maxTotal = Math.max(maxTotal, dto.getTotal());
        }
        if (maxTotal >= 10000 && maxTotal < 10000 * 10000) {
            unit = "万（次）";
        } else if (maxTotal >= 10000 * 10000 && maxTotal < 10000 * 10000 * 10000L) {
            unit = "亿（次）";
        } else {
            unit = "（次）";
        }
        return unit;
    }

    /**
     * 设置单位（计量单位-字节数）
     *
     * @param sourceDataList
     * @return
     */
    private static String getUnit4Metering(List<APICallTotalAnalysisDTO> sourceDataList) {
        String unit = null;
        long maxTotal = 0;
        for (APICallTotalAnalysisDTO dto : sourceDataList) {
            maxTotal = Math.max(maxTotal, dto.getTotal());
        }
        if (maxTotal >= 1024 && maxTotal < 1024 * 1024) {
            unit = "KB";
        } else if (maxTotal >= 1024 * 1024 && maxTotal < 1024 * 1024 * 1024L) {
            unit = "MB";
        } else if (maxTotal >= 1024 * 1024 * 1024L && maxTotal < 1024 * 1024 * 1024 * 1024L) {
            unit = "GB";
        } else {
            unit = "B";
        }
        return unit;
    }

}
