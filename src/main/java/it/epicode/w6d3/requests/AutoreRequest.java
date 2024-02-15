package it.epicode.w6d3.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.w6d3.model.Post;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.util.List;

public class AutoreRequest {

    @NotNull(message = "Nome obbligatorio")
    @NotEmpty(message = "Nome non vuoto")
    private String nome;
    @NotNull(message = "Cognome obbligatorio")
    @NotEmpty(message = "Cognome non vuoto")
    private String cognome;
    @Email
    private String email;
    @NotNull(message = "Data di nascita obbligatorio")
    private LocalDate dataDiNascita;
    @URL
    private String avatar;
    private List<Post> posts;


}
