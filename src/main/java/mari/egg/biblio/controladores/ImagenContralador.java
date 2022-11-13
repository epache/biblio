
package mari.egg.biblio.controladores;

import mari.egg.biblio.entidades.Usuario;
import mari.egg.biblio.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imagen")
public class ImagenContralador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable String id){
        Usuario usuario= usuarioServicio.getOne(id);
        //AREGLO PARA TRAER EL CONTENIDO PARA QUE SE PROYECTE EN LA WEB
       byte[] imagen= usuario.getImagen().getContenido();
       
       //esto va a decir que tipo de contenido es, en este caso es una imagen
       HttpHeaders headers =new HttpHeaders();
       headers.setContentType(MediaType.IMAGE_JPEG);
       
       //estado en que termina el proceso... estadohttp--> estado 200 ok!
       
       
       return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
    
    
    
    
}
