package com.common.util;

import com.common.excption.AuthExceptionConstants;
import com.common.excption.BaseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/20 16:47
 * @Description:
 */
public class CarPlateNumberUtil {

    /**
     * 车牌号正则
     */
    private static final String pattern = "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{1}(([A-HJ-Z]{1}[A-HJ-NP-Z0-9]{5})|([A-HJ-Z]{1}(([DF]{1}[A-HJ-NP-Z0-9]{1}[0-9]{4})|([0-9]{5}[DF]{1})))|([A-HJ-Z]{1}[A-D0-9]{1}[0-9]{3}警)))|([0-9]{6}使)|((([沪粤川云桂鄂陕蒙藏黑辽渝]{1}A)|鲁B|闽D|蒙E|蒙H)[0-9]{4}领)|(WJ[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼·•]{1}[0-9]{4}[TDSHBXJ0-9]{1})|([VKHBSLJNGCE]{1}[A-DJ-PR-TVY]{1}[0-9]{5})";


    /**
     * 校验车牌号
     * @param carNo
     * @return
     */
    public static boolean check (String carNo) {
        Pattern patte = Pattern.compile(pattern);
        Matcher matcher = patte.matcher(carNo);
        if (!matcher.matches()) {
            throw BaseException.build(AuthExceptionConstants.CHECK_CAR_NO);
        }else{
           return true;
        }
    }
}
