package com.semana2.demo.controller;

import com.semana2.demo.model.*;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping(value = "/producto")
public class ProductoController {
    private Map<String, Producto> productos;
    public ProductoController() {
        productos = new HashMap<String, Producto>();
        Producto p = new Producto();
        String id = UUID.randomUUID().toString();
        p.setId(id);
        p.setNombre("Cargador");;
        p.setDescription("GMOBILE");;
        p.setImagen("https://i1.wp.com/maemaemae.xyz/wp-content/uploads/2016/12/61DgnEtcDDL._SL1200_.jpg");
        p.setPrecio(59.9);

        productos.put(id, p);
    }


    @PostMapping(value = "/crear", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> crearProducto(@RequestBody Producto p){
        String id = UUID.randomUUID().toString();
        p.setId(id);
        productos.put(p.getId(), p);
        return new ResponseEntity<String>(id,HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<Map<String,Producto>> listarProducto(){
        return new ResponseEntity<Map<String,Producto>>(productos,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Producto> buscarProducto(@PathVariable String id){
        if(productos.containsKey(id)){
            Producto p = productos.get(id);
            return new ResponseEntity<Producto>(p, HttpStatus.OK);
        }else{
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/modificar", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> modificarProducto(@RequestBody String id){
        if(productos.containsKey(id)){
            Producto p = productos.get(id);
            p.setDescription("Bat");
            p.setNombre("Samsung");
            p.setPrecio(79.9);
            return new ResponseEntity<String>(id, HttpStatus.OK);
        }

        return new ResponseEntity<String>(id,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/eliminar", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> eliminarProducto(@RequestParam String id){
        if(productos.containsKey(id)){
            productos.remove(id);
            return new ResponseEntity<String>("Se eliminó el producto", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }

    }

}
