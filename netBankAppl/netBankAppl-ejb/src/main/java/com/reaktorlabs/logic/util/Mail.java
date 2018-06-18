/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.logic.util;

/**
 *
 * @author Paszternák László
 */
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.inject.Inject;
import javax.mail.Address;

@Stateless
@Asynchronous
public class Mail {

    @Resource(lookup = "java:jboss/mail/gmail")
    private Session session;
    private BankAccountNumberGenerator bankAccountNumberGenerator;

    @Inject
    public Mail(BankAccountNumberGenerator bankAccountNumberGenerator) {
        this.bankAccountNumberGenerator = bankAccountNumberGenerator;
    }

    public Mail() {
    }

    public void send(String addresses) {

        try {

            Message message = new MimeMessage(session);
            Address address = new InternetAddress("takimalac@gmail.com");
            message.setFrom(address);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject("Számlanyitási kérelem");
            message.setText("Kedves Ügyfelünk!\n Köszönjük bizalmát!\n"
                    + "Számlanyitáshoz szükséges bankszámlaszáma: \n"
                    + bankAccountNumberGenerator.bankAccountNumberGenerator() + " \n"
                    + "Kérjük az alábbi linken szíveskedjen folytatni a regisztrációt: http://localhost:8080/netBankAppl-web-1.0-SNAPSHOT/faces/register.xhtml");
            Transport.send(message);
        } catch (MessagingException e) {
            Logger.getLogger(Mail.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
    }
}
