package it.epicode.w6d3.service;

import it.epicode.w6d3.exception.NotFoundException;
import it.epicode.w6d3.model.Autore;
import it.epicode.w6d3.repository.AutoreRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    public Page<Autore> cercaTuttiGliAutori(Pageable pageable){

        return autoreRepository.findAll(pageable);

    }


    public Autore cercaAutorePerId(int id) throws NotFoundException {

        return autoreRepository.findById(id).orElseThrow(() -> new NotFoundException("Autore con id: " + id + " non trovato"));

    }


    public Autore salvaAutore(Autore autore){

        return autoreRepository.save(autore);


    }


    public Autore aggiornaAutore(int id, Autore autore) throws NotFoundException{

        Autore a = cercaAutorePerId(id);

        a.setNome(autore.getNome());
        a.setCognome(autore.getCognome());
        a.setAvatar(autore.getAvatar());
        a.setEmail(autore.getEmail());
        a.setDataDiNascita(autore.getDataDiNascita());

        return autoreRepository.save(a);

    }


    public void eliminaAutore(int id) throws NotFoundException{

        Autore a = cercaAutorePerId(id);
        autoreRepository.delete(a);

    }

    public Autore uploadAutore(int id, String url) throws NotFoundException{
        Autore autore = cercaAutorePerId(id);

        auto.setLogo(url);
        return autoRepository.save(auto);
    }

}
