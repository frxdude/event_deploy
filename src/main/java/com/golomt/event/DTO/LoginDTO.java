package com.golomt.event.DTO;

import org.springframework.beans.factory.annotation.Value;

public interface LoginDTO {

    @Value("#{target.role_id}")
    String getRoleId();

    @Value("#{target.qr_access}")
    String getQrAccess();

    @Value("#{target.upload_access}")
    String getUploadAccess();

    @Value("#{target.jack_access}")
    String getJackAccess();
}
