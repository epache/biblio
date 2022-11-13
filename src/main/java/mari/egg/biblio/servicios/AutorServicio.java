package mari.egg.biblio.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import mari.egg.biblio.entidades.Autor;
import mari.egg.biblio.entidades.Libro;
import mari.egg.biblio.excepciones.MiException;
import mari.egg.biblio.repositorios.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiException {
        validar(nombre);

        Autor autor = new Autor();
        autor.setNombre(nombre);
        autorRepositorio.save(autor);
    }

    public List<Autor> listarAutor() {

        List<Autor> autores = new ArrayList();
        autores = autorRepositorio.findAll();

        return autores;

    }

    public void modificarAutor(String nombre, String id) throws MiException{

        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();
            autor.setNombre(nombre);

            autorRepositorio.save(autor);
        }
    }

    public Autor getOne(String id) {
        return autorRepositorio.getOne(id);
    }

    public void validar(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El titulo no puede ser vacio ni nulo");
        }
    }
}
