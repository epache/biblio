package mari.egg.biblio.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import mari.egg.biblio.entidades.Editorial;
import mari.egg.biblio.excepciones.MiException;
import mari.egg.biblio.repositorios.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    @Autowired
    EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MiException {
       validar(nombre);
       
             Editorial editorial = new Editorial();
             editorial.setNombre(nombre);
            
             editorialRepositorio.save(editorial);
             
       
       
    }

    public List<Editorial> listarEditoriales() {

        List<Editorial> editoriales = new ArrayList();
        editoriales = editorialRepositorio.findAll();

        return editoriales;

    }

    public void modificarEditorial(String nombre, String id) {

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);

            editorialRepositorio.save(editorial);
        }

    }
    
     public void validar(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre de la editorial no puede ser vacio ni nulo");
        }
    }
    }
