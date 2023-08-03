package com.web.client.webClient.web.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommandeDto {

    private String name;
    private String code;
}
