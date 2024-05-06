/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltv.web.services;


import javax.mail.MessagingException;
import qltv.web.dto.DataMailDTO;

/**
 *
 * @author DELL
 */
public interface MailService {
        void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}
