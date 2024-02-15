package it.epicode.w6d3.service;

import it.epicode.w6d3.exception.NotFoundException;
import it.epicode.w6d3.model.Autore;
import it.epicode.w6d3.repository.AutoreRepository;
import it.epicode.w6d3.requests.AutoreRequest;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public Page<Autore> cercaTuttiGliAutori(Pageable pageable){

        return autoreRepository.findAll(pageable);

    }


    public Autore cercaAutorePerId(int id) throws NotFoundException {

        return autoreRepository.findById(id).orElseThrow(() -> new NotFoundException("Autore con id: " + id + " non trovato"));

    }


    public Autore salvaAutore(AutoreRequest autoreRequest){

        Autore a = new Autore();
        a.setNome(autoreRequest.getNome());
        a.setCognome(autoreRequest.getCognome());
        a.setEmail(autoreRequest.getEmail());
        a.setAvatar(autoreRequest.getAvatar());
        a.setDataDiNascita(autoreRequest.getDataDiNascita());
        a.setPosts(autoreRequest.getPosts());
        sendMail(a.getEmail());

        return autoreRepository.save(a);

    }


    public Autore aggiornaAutore(int id, AutoreRequest autoreRequest) throws NotFoundException{

        Autore a = cercaAutorePerId(id);

        a.setNome(autoreRequest.getNome());
        a.setCognome(autoreRequest.getCognome());
        a.setAvatar(autoreRequest.getAvatar());
        a.setEmail(autoreRequest.getEmail());
        a.setDataDiNascita(autoreRequest.getDataDiNascita());

        return autoreRepository.save(a);

    }


    public void eliminaAutore(int id) throws NotFoundException{

        Autore a = cercaAutorePerId(id);
        autoreRepository.delete(a);

    }

    public Autore uploadAvatar(int id, String url) throws NotFoundException{
        Autore autore = cercaAutorePerId(id);

        autore.setAvatar(url);
        return autoreRepository.save(autore);
    }


    private void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Servizio rest");
        message.setText("Registrazione al servizio rest avvenuta con successo");

        javaMailSender.send(message);
    }

}
