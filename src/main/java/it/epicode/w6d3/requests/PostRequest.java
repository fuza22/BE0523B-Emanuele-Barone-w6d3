package it.epicode.w6d3.requests;

import it.epicode.w6d3.model.Categoria;
import lombok.Data;

@Data
public class PostRequest {

    private String titolo;
    private Categoria categoria;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;
    private int autoreId;

}
