package com.hx.core.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Auther: Zhoudu
 * @Date: 2018/7/16 17:53
 * @Description:
 */
public interface Enums {

    enum EnumYear {

        YEAR_ALL(0, "不限"),
        YEAR_60(1960, "60"),
        YEAR_70(1970, "70"),
        YEAR_80(1980, "80"),
        YEAR_90(1990, "90"),
        YEAR_00(2000, "00"),
//        YEAR_10(2010, "10"),
//        YEAR_20(2020, "20"),
//        YEAR_30(2030, "30"),
        ;
        EnumYear(int no, String year) {
            this.no = no;
            this.year = year;
        }

        public static int getYearNo(String year) {
            Optional<EnumYear> optional = Arrays.asList(values()).stream().filter(l -> l.year.equals(year)).findFirst();
            if (optional.isPresent()) {
                return optional.get().no;
            }
            return 0;
        }

        public static Map<Integer,String> getAll(){
            Map<Integer,String> map = new HashMap<>();
            Arrays.asList(values()).stream().forEach(l -> map.put(l.no,l.year));
            return map;
        }

        private int no;
        private String year;

    }

    enum OrderBy{
        SERVICE_COUNT(1,"service_count"),
        SCORE(2,"score"),;
        private int number;

        private String column;

        OrderBy(int number, String column) {
            this.number = number;
            this.column = column;
        }
        public static String getColumn(int number) {
            Optional<OrderBy> optional = Arrays.asList(values()).stream().filter(l -> l.number == number).findFirst();
            if (optional.isPresent()) {
                return optional.get().column;
            }
            return "";
        }
    }

}
