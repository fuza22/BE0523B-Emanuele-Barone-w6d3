package it.epicode.w6d3.service;

import it.epicode.w6d3.model.Autore;
import it.epicode.w6d3.model.Post;
import it.epicode.w6d3.exception.NotFoundException;
import it.epicode.w6d3.model.Autore;
import it.epicode.w6d3.model.Post;
import it.epicode.w6d3.repository.PostRepository;
import it.epicode.w6d3.requests.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    AutoreService autoreService;


    public Page<Post> cercaTuttiIPost(Pageable pageable){

        return postRepository.findAll(pageable);

    }


    public Post cercaPostPerId(int id) throws NotFoundException {

        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post con id: " + id + "non trovato"));

    }

    public Post salvaPost(PostRequest post) throws NotFoundException{

        Post p = new Post();
        Autore a = autoreService.cercaAutorePerId(post.getAutoreId());
        p.setAutore(a);
        p.setTitolo(post.getTitolo());
        p.setCategoria(post.getCategoria());
        p.setContenuto(post.getContenuto());
        p.setTempoDiLettura(post.getTempoDiLettura());
        return postRepository.save(p);


    }


    public Post aggiornaPost(int id, PostRequest post) throws NotFoundException{

        Post p = cercaPostPerId(id);
        Autore a = autoreService.cercaAutorePerId(post.getAutoreId());

        p.setAutore(a);
        p.setTitolo(post.getTitolo());
        p.setCategoria(post.getCategoria());
        p.setContenuto(post.getContenuto());
        p.setTempoDiLettura(post.getTempoDiLettura());
        return postRepository.save(p);


    }

    public void eliminaPost(int id) throws NotFoundException{

        Post p = cercaPostPerId(id);
        postRepository.delete(p);

    }

    public Post uploadCover(int id, String url) throws NotFoundException{
        Post post = cercaPostPerId(id);

        post.setCover(url);
        return postRepository.save(post);
    }

}
