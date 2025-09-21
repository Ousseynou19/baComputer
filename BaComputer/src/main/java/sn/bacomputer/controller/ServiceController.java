package sn.bacomputer.controller;


import org.springframework.web.bind.annotation.*;
import sn.bacomputer.DTO.ServiceDTO;
import sn.bacomputer.service.ServiceBusiness;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    private final ServiceBusiness serviceBusiness;

    public ServiceController(ServiceBusiness serviceBusiness) {
        this.serviceBusiness = serviceBusiness;
    }

    @PostMapping
    public ServiceDTO create(@RequestBody ServiceDTO dto) {
        return serviceBusiness.create(dto);
    }

    @GetMapping
    public List<ServiceDTO> listAll() {
        return serviceBusiness.listAll();
    }

    @GetMapping("/{id}")
    public ServiceDTO get(@PathVariable Long id) {
        return serviceBusiness.get(id);
    }

    @PutMapping("/{id}")
    public ServiceDTO update(@PathVariable Long id, @RequestBody ServiceDTO dto) {
        return serviceBusiness.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        serviceBusiness.delete(id);
    }
}
