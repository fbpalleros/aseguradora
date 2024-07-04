package org.aseguradora.services.impl;

import com.lowagie.text.DocumentException;

import org.aseguradora.entity.Policy;
import org.aseguradora.repositories.PolicyRepository;
import org.aseguradora.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
public class PolicyServiceImpl implements PolicyService {

    PolicyRepository policyRepository;
    
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;


    @Autowired
    public PolicyServiceImpl(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public List<Policy> findAll() {
        return policyRepository.findAll();
    }

    @Override
    public Policy findById(Long id) {
        return policyRepository.findById(id);
    }

    @Override
    public List<Policy> findByCustomerId(Long id) {
        return policyRepository.findByCustomerId(id);
    }

    @Override
    public void save(Policy policy) {
        policyRepository.save(policy);
    }

    @Override
    public void delete(Policy policy) {
        policyRepository.delete(policy);
    }
    
    @Override
    public void sendNotificacion(Policy policy) {

            Context context = new Context();
            context.setVariable("policy", policy);

            String htmlAsString =  springTemplateEngine.process("poliza/email", context);
            String policyName = "policy-" + policy.getId() + ".pdf";

            String outputFolder = System.getProperty("user.home") + File.separator + policyName;

            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(outputFolder);

                String baseUrl = FileSystems.getDefault()
                        .getPath("src/main/webapp/WEB-INF/")
                        .toUri().toURL().toString();


                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(htmlAsString, baseUrl);
                renderer.layout();
                renderer.createPDF(outputStream);
                outputStream.close();


                Path path = Paths.get(outputFolder);
                byte[] content = new byte[0];

                content = Files.readAllBytes(path);

                MimeMessage message = emailSender.createMimeMessage();

                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom("vivirseguros@microneuronal.com");
                helper.setTo(policy.getCustomer().getEmail());
                helper.setSubject("Su seguro ya esta registrado");
                helper.setText("Estimado cliente su seguro se encuentra ya disponible");
                helper.addAttachment(policyName, new ByteArrayResource(content));
                emailSender.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }



}
