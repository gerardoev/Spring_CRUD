package com.gerardoev.clients.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    private Long id;
    private String nombre;
    private String correo;
}
