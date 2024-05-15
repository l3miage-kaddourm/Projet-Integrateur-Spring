package fr.uga.l3miage.integrator.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateRequest {

    private String email;
}
