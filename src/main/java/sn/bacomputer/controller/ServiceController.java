package sn.bacomputer.controller;

import sn.bacomputer.dto.ServiceDTO;
import sn.bacomputer.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Service entities.
 */
@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    /**
     * GET /api/services : Get all Services.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of services in body
     */
    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        List<ServiceDTO> list = serviceService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * GET /api/services/{id} : Get the service by id.
     *
     * @param id the id of the service to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the serviceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getService(@PathVariable Long id) {
        ServiceDTO serviceDTO = serviceService.findOne(id);
        return ResponseEntity.ok(serviceDTO);
    }

    /**
     * POST /api/services : Create a new service.
     *
     * @param serviceDTO the serviceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new serviceDTO
     */
    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO serviceDTO) {
        ServiceDTO result = serviceService.save(serviceDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * PUT /api/services : Updates an existing service.
     *
     * @param serviceDTO the serviceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated serviceDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<ServiceDTO> updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO) {
        serviceDTO.setId(id);
        ServiceDTO result = serviceService.update(serviceDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * DELETE /api/services/{id} : delete the "id" service.
     *
     * @param id the id of the service to delete
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
