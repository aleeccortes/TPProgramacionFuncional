package org.example.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Libro {
    private String titulo;
    private  String autor;
    private  int paginas;
    private  double precio;



    @Override
    public String toString() {
        return titulo + " (" + autor + ", " + paginas + " pags, $" + precio + ")";
    }
}
