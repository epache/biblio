package mari.egg.biblio.controladores;

import java.util.List;
import mari.egg.biblio.entidades.Autor;
import mari.egg.biblio.entidades.Editorial;
import mari.egg.biblio.entidades.Libro;
import mari.egg.biblio.excepciones.MiException;
import mari.egg.biblio.servicios.AutorServicio;
import mari.egg.biblio.servicios.EditorialServicio;
import mari.egg.biblio.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutor();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) long isbn, @RequestParam String titulo, @RequestParam(required = false) Integer ejemplares,
            @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap modelo) {
        // el required false hace que aunque venga el isbn vacio o nulo lo deja ingresar al metodo y se trata con la excepcion
        try {
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "El libro fue cargado correctamente");

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage()); //manejo error con thymeleaf

            List<Autor> autores = autorServicio.listarAutor();
            List<Editorial> editoriales = editorialServicio.listarEditoriales();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);

            return "libro_form.html"; //vuelve a cargarse el formulario
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Libro> libros = libroServicio.listarLibro();
        modelo.addAttribute("libros", libros);
        return "libro_list.html";
    }

}
