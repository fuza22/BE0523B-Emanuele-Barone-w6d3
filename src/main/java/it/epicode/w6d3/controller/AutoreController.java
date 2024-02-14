package it.epicode.w6d3.controller;

import it.epicode.w6d3.exception.NotFoundException;
import it.epicode.w6d3.model.Autore;
import it.epicode.w6d3.model.CustomResponse;
import it.epicode.w6d3.repository.AutoreRepository;
import it.epicode.w6d3.service.AutoreService;
import org.apache.catalina.util.CustomObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/autore")
public class AutoreController {

    @Autowired
    AutoreService autoreService;


    @GetMapping("")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable){

        try{

            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.cercaTuttiGliAutori(pageable), HttpStatus.OK);

        }catch(Exception e){

            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getAutoreById(@PathVariable int id){

        try {
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.cercaAutorePerId(id), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("")
    public ResponseEntity<CustomResponse> saveAutore(@RequestBody Autore autore){

        try{
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.salvaAutore(autore), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateAutore(@PathVariable int id, @RequestBody Autore autore){

        try {
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.aggiornaAutore(id, autore), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteAutore(@PathVariable int id){

        try{
            autoreService.eliminaAutore(id);
            return CustomResponse.emptyResponse("Autore con id=" + id + " cancellato", HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
