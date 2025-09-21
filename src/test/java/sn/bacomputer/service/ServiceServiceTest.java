package sn.bacomputer.service;

import sn.bacomputer.entity.Service;
import sn.bacomputer.dto.ServiceDTO;
import sn.bacomputer.mapper.ServiceMapper;
import sn.bacomputer.repository.ServiceRepository;
import sn.bacomputer.service.impl.ServiceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceServiceTest {

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ServiceMapper serviceMapper;

    @InjectMocks
    private ServiceServiceImpl serviceService;

    private Service service;
    private ServiceDTO serviceDTO;
    private List<Service> serviceList;
    private Long testId;

    @BeforeEach
    void setUp() {
        // Setup test data
        testId = 1L;

        service = new Service();
        // Set id directly on the entity using reflection or direct field access if needed
        // For test purposes, we'll mock the responses instead of relying on actual entity getter methods

        serviceDTO = new ServiceDTO();
        serviceDTO.setId(testId);

        serviceList = new ArrayList<>();
        serviceList.add(service);
    }

    @Test
    void testSave() {
        // Arrange
        when(serviceMapper.toEntity(any(ServiceDTO.class))).thenReturn(service);
        when(serviceRepository.save(any(Service.class))).thenReturn(service);
        when(serviceMapper.toDto(any(Service.class))).thenReturn(serviceDTO);

        // Act
        ServiceDTO result = serviceService.save(serviceDTO);

        // Assert
        assertThat(result).isNotNull();
        verify(serviceRepository).save(any(Service.class));
        verify(serviceMapper).toDto(any(Service.class));
    }

    @Test
    void testFindAll() {
        // Arrange
        List<ServiceDTO> dtoList = new ArrayList<>();
        dtoList.add(serviceDTO);

        when(serviceRepository.findAll()).thenReturn(serviceList);

        // Stub the individual entity mapping instead of the list mapping
        // This correctly handles the stream().map() call in the service
        when(serviceMapper.toDto(any(Service.class))).thenReturn(serviceDTO);

        // Act
        List<ServiceDTO> result = serviceService.findAll();

        // Assert
        assertThat(result).isNotNull().hasSize(1);
        verify(serviceRepository).findAll();
    }

    @Test
    void testFindOne() {
        // Arrange
        when(serviceRepository.findById(any())).thenReturn(Optional.of(service));
        when(serviceMapper.toDto(any(Service.class))).thenReturn(serviceDTO);

        // Act
        ServiceDTO result = serviceService.findOne(testId);

        // Assert
        assertThat(result).isNotNull();
        verify(serviceRepository).findById(any());
    }

    @Test
    void testDelete() {
        // Arrange
        doNothing().when(serviceRepository).deleteById(any());

        // Act
        serviceService.delete(testId);

        // Assert
        verify(serviceRepository).deleteById(any());
    }
}
