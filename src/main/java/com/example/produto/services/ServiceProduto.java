package com.example.produto.services;

import com.example.produto.models.Produto;
import com.example.produto.repositories.RepositoryProduto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProduto {
    private RepositoryProduto rp;

    private ServiceProduto(RepositoryProduto rp) {
        this.rp = rp;
    }

    public List<Produto> getAll() {
        return rp.findAll();
    }

    public Produto create(Produto p) {
        return rp.save(p);
    }

    public void delete(Long id) {
        rp.deleteById(id);
    }

    public Produto put(Long id, Produto p) {
        Optional<Produto> curr = rp.findById(id);
        curr.ifPresent(produto -> p.setId(produto.getId()));
        return rp.save(p);
    }
}
