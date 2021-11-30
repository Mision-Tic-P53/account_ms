package co.misiontic.p53.account_ms.controllers;

import co.misiontic.p53.account_ms.models.EstadoPago;
import co.misiontic.p53.account_ms.models.TiposDocumento;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo/{mensajeEstado}")
    public String Prueba(@PathVariable int mensajeEstado){
        if(EstadoPago.Aprobado.ordinal() == mensajeEstado) {
            return "Transaccion Aprobada";
        } else if(EstadoPago.Inicializado.ordinal() == mensajeEstado) {
            return "Transacicon iniciada";
        }

        if(TiposDocumento.Cedula.SIGLA.equals("CC") ){
            return "SI, haga la logica para cedula";
        }

        return TiposDocumento.Pasaporte.SIGLA;
    }

}
