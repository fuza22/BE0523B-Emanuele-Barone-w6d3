package it.epicode.w6d3.requests;

import it.epicode.w6d3.model.Categoria;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

@Data
public class PostRequest {

    @NotNull(message = "Titolo obbligatorio")
    @NotEmpty(message = "Titolo non vuoto")
    private String titolo;
    @NotNull(message = "Categoria obbligatorio")
    private Categoria categoria;
    @URL
    private String cover;
    @NotNull(message = "Contenuto obbligatorio")
    @NotEmpty(message = "Convenuto non vuoto")
    private String contenuto;
    @Min(value = 1)
    private int tempoDiLettura;
    private int autoreId;

}
