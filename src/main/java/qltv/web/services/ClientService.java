/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltv.web.services;

import qltv.web.dto.sdi.ClientSdi;

/**
 *
 * @author DELL
 */
public interface ClientService {
    Boolean create(ClientSdi sdi);
    
    String getGeneratedOTP();
}
