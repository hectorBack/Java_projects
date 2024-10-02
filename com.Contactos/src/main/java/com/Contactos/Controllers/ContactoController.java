package com.Contactos.Controllers;

import com.Contactos.Entities.Contacto;
import com.Contactos.Services.ContactoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contactos")
public class ContactoController {

    @Autowired
    private ContactoServices contactoServices;

    @GetMapping
    public String listarContactos(Model model) {
        model.addAttribute("contactos", contactoServices.getAllContactos());
        return "listar_contactos";  // nombre del archivo HTML
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoContacto(Model model) {
        Contacto contacto = new Contacto();
        model.addAttribute("contacto", contacto);
        return "nuevo_contacto";  // nombre del archivo HTML
    }

    @PostMapping
    public String guardarContacto(@ModelAttribute("contacto") Contacto contacto) {
        contactoServices.saveContacto(contacto);
        return "redirect:/contactos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Contacto contacto = contactoServices.getContactoById(id);
        model.addAttribute("contacto", contacto);
        return "editar_contacto";  // nombre del archivo HTML
    }

    @PostMapping("/{id}")
    public String actualizarContacto(@PathVariable Long id, @ModelAttribute("contacto") Contacto contacto) {
        contactoServices.updateContacto(id, contacto);
        return "redirect:/contactos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarContacto(@PathVariable Long id) {
        contactoServices.deleteContacto(id);
        return "redirect:/contactos";
    }
}

