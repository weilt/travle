package com.admin.service.api.impl;

import com.admin.service.api.ImgService;
import com.domain.plus.entity.PlusImg;
import com.domain.plus.mapper.PlusImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/16 09:52
 * @Description:
 */
@Service
public class ImgServiceImpl implements ImgService {


    @Autowired
    private PlusImgMapper mapper;

    @Override
    public List<String> getAdvertisement(Integer code) {
        List<PlusImg> list = mapper.queryImgByType(code);
        List<String> paths = new ArrayList<>();
        if (null != list && !list.isEmpty()){
            list.stream().forEach(l -> paths.add(l.getImgUrl()));
        }
        return paths;
    }

    @Override
    public String getImg(Integer code) {
        PlusImg plusImg = mapper.queryImgByTypeLimit1(code);
        return plusImg == null?"":plusImg.getImgUrl();
    }
}
