package com.example.springboot.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.models.Book;
import com.example.springboot.models.Product;
import com.example.springboot.models.UserData;
import com.example.springboot.mybeans.MiBean;
import com.example.springboot.mybeans.MiComponente;
import com.example.springboot.services.IOrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class Controller {

    private IOrderService iOrderService;
    private MiBean miBean;
    private MiComponente miComponente;

    public Controller(IOrderService iOrderService, MiBean miBean, MiComponente miComponente) {
        this.iOrderService = iOrderService;
        this.miBean = miBean;
        this.miComponente = miComponente;
    }

    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    @PostMapping("/libro")
    String guardarLibro(@RequestBody Book libro) {
        logger.debug("llave {} valor {}", libro.name, libro.editorial);

        return "Libro guardado";
    }

    @GetMapping(value = "/saludar")
    @ResponseStatus(value = HttpStatus.MOVED_PERMANENTLY, reason = "FUE MOVIDA")
    public String miSegundaRutaStatus() {
        return "Aprendiendo STATUS";
    }

    @GetMapping(value = "/animales/{lugar}")
    public ResponseEntity<String> getAnimals(@PathVariable String lugar) {
        if (lugar.equals("granja")) {
            return ResponseEntity.status(HttpStatus.OK).body("Caballo, ovejar, otros");
        } else if (lugar.equals("selva")) {
            return ResponseEntity.status(HttpStatus.OK).body("mono, gorila");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lugar no valido");
        }
    }

    @GetMapping(value = "/calcular/{numero}")
    public int getCalculo(@PathVariable int numero) {
        throw new NullPointerException("Esta excepcion porque si");
    }

    @GetMapping(value = "/userData")
    public ResponseEntity<String> getUserData() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body("{\"name\": \"mary\"}");
    }

    @GetMapping(value = "/userData/v2")
    public Map<String, Map<String, Object>> getUserDataV2() {
        return Map.of("user",
                Map.of("name", "mary", "age", 25));
    }

    @GetMapping(value = "/userData/v3")
    public UserData getUserDatav3() {
        return new UserData("name", 25, "alameda");
    }

    @PostMapping(value = "/order")
    public String crearOrden(@RequestBody List<Product> products) {
        iOrderService.saveOrder(products);
        return "products";
    }

    @GetMapping(value = "/mibean")
    public String saludarDesdeBean() {
        miBean.saludar();
        return "Complete";
    }

    @GetMapping(value = "/micomponente")
    public String saludarDesdeComponente() {
        miComponente.saludarDesdeComponente();
        return "Reallizado";
    }

}
