package com.example.produto.controllers;

import com.example.produto.models.Produto;
import com.example.produto.repositories.RepositoryProduto;
import com.example.produto.services.ServiceProduto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping(path = "/produtos")
public class ControllerProduto {
    private ServiceProduto sv;
    private ControllerProduto(ServiceProduto sv) {
        this.sv = sv;
    }

    @GetMapping("/")
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(sv.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<Produto> create(@RequestBody Produto p) {
        Produto created = sv.create(p);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> put(@PathVariable Long id, @RequestBody Produto p) {
        Produto created = sv.put(id, p);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sv.delete(id);
        return ResponseEntity.noContent().build();
    }
}
