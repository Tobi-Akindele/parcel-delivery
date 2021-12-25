package com.tobiakindele.parceldelivery.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author oyindamolaakindele
 */
public class ParcelDeliveryDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;
    private String description;
    private String pickupAddress;
    private String destinationAddress;
    private String status;
    private Date createdAt;
    private Long createdBy;
    private Long pickedupBy;
    private UserDto createdByDto;
    private UserDto pickedByDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getPickedupBy() {
        return pickedupBy;
    }

    public void setPickedupBy(Long pickedupBy) {
        this.pickedupBy = pickedupBy;
    }

    public UserDto getCreatedByDto() {
        return createdByDto;
    }

    public void setCreatedByDto(UserDto createdByDto) {
        this.createdByDto = createdByDto;
    }

    public UserDto getPickedByDto() {
        return pickedByDto;
    }

    public void setPickedByDto(UserDto pickedByDto) {
        this.pickedByDto = pickedByDto;
    }

    @Override
    public String toString() {
        return "ParcelDeliveryDto{" + "id=" + id + ", name=" + name + ", description=" + description + ", pickupAddress=" + pickupAddress + ", destinationAddress=" + destinationAddress + ", status=" + status + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", pickedupBy=" + pickedupBy + ", createdByDto=" + createdByDto + ", pickedByDto=" + pickedByDto + '}';
    }
}
