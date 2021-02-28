package com.golomt.event.Helper;

import okhttp3.*;

import java.io.File;
import java.io.IOException;
import com.golomt. event.DTO.SMSDTO;

public class SMSHelper {
    OkHttpClient client = new OkHttpClient();
    public String sendSMS(SMSDTO smsDto) throws IOException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
                "<Alert_SMS_Req xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"a Alert_SMS_Req.xsd\">" +
                    "<request>" +
                    "<smsMessage>" + smsDto.getText() + "</smsMessage>" +
                    "<channelType>006</channelType>" +
                    "<mobileNo>+976" + smsDto.getMobileNo() + "</mobileNo>" +
                    "<type>0</type>" +
                    "<dept>CMD</dept>" +
                    "<sec>EVT</sec>" +
                    "<product>SMS</product>" +
                    "<cat>ALERTS</cat>" +
                    "<ref>EVTN23TY1</ref>" +
                    "<rsv/>" +
                    "</request>" +
                "</Alert_SMS_Req>";
        RequestBody body = RequestBody.create(MediaType.parse("text/xml"), xml);
        Request request = new Request.Builder().url("http://192.168.208.107:8343/api/user/")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
