package com.qlvb.vnpt.botttt.schedule.domain.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<?>> getData() {
        HashMap<String, List<?>> expandableListDetail = new LinkedHashMap<String, List<?>>();

        List<Object> cricket = new ArrayList<>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa");

        List<Object> football = new ArrayList<>();
        football.add("View");
//        football.add("Spain");
//        football.add("Germany");
//        football.add("Netherlands");
//        football.add("Italy");

        List<TrinhKyModel> basketball = new ArrayList<>();
        basketball.add(new TrinhKyModel("1", "Đơn vị", "Người ký", "Trạng thái", "Ngày ký", "Ngày gửi"));
        basketball.add(new TrinhKyModel("1", "Phòng dịch vụ viễn thông", "tuanvn", "Đã duyệt", "19/12/2017", "19/12/2017"));
        basketball.add(new TrinhKyModel("1", "Phòng dịch vụ viễn thông", "tuanvn", "Đã duyệt", "19/12/2017", "19/12/2017"));
        basketball.add(new TrinhKyModel("1", "Phòng dịch vụ viễn thông", "tuanvn", "Đã duyệt", "19/12/2017", "19/12/2017"));
        basketball.add(new TrinhKyModel("1", "Phòng dịch vụ viễn thông", "tuanvn", "Đã duyệt", "19/12/2017", "19/12/2017"));

        expandableListDetail.put("Văn bản liên quan", cricket);
        expandableListDetail.put("Nội dung trình ký", football);
        expandableListDetail.put("Danh sách trình ký", basketball);

        return expandableListDetail;
    }
}
