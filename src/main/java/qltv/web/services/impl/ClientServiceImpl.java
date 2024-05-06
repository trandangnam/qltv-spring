/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltv.web.services.impl;

import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import qltv.web.dto.DataMailDTO;
import qltv.web.dto.sdi.ClientSdi;
import qltv.web.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qltv.web.services.MailService;
import qltv.web.utils.Const;
import qltv.web.utils.DataUtils;

/**
 *
 * @author DELL
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private MailService mailService;
    public String generatedOTP;

    @Override
    public Boolean create(ClientSdi sdi) {
        generatedOTP = DataUtils.generateTempPwd(6);
//        this.generatedOTP = generatedOTP;
        try {
            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(sdi.getEmail());
            dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            props.put("name", sdi.getName());
            props.put("username", sdi.getUsername());
            props.put("password", generatedOTP);
            dataMail.setProps(props);

            mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
            return true;
        } catch (MessagingException exp) {
            exp.printStackTrace();
        }
        return false;
    }

    public String getGeneratedOTP() {
        return generatedOTP;
    }

}
