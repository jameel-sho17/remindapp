package com.elsderremapp.remiapp.dto;

import lombok.Data;

@Data
public class ConnectUsingCodeRequestDTO {
    private long caregiverId;
    private String code;
}
