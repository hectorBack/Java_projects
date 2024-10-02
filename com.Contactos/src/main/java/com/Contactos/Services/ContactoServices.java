package com.Contactos.Services;

import com.Contactos.Entities.Contacto;
import com.Contactos.Repositories.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoServices {

    @Autowired
    private ContactoRepository contactoRepository;

    public List<Contacto> getAllContactos() {
        return contactoRepository.findAll();
    }

    public Contacto saveContacto(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    public void deleteContacto(Long id) {
        contactoRepository.deleteById(id);
    }

    public Contacto updateContacto(Long id, Contacto contactoDetails) {
        Contacto contacto = contactoRepository.findById(id).orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
        contacto.setNombre(contactoDetails.getNombre());
        contacto.setTelefono(contactoDetails.getTelefono());
        contacto.setEmail(contactoDetails.getEmail());
        contacto.setDireccion(contactoDetails.getDireccion());
        return contactoRepository.save(contacto);
    }

    public Contacto getContactoById(Long id) {
        return contactoRepository.getById(id);
    }
}
