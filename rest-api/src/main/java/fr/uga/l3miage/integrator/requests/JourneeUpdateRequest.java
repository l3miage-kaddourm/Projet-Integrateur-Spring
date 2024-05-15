package fr.uga.l3miage.integrator.requests;

import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class JourneeUpdateRequest {
    private final EtatsDeJournee etatDeJournee;

    public JourneeUpdateRequest(){
        this.etatDeJournee = null;
    }
    public JourneeUpdateRequest(EtatsDeJournee etat) {
        this.etatDeJournee = etat;
    }
}