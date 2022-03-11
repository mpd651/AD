package es.marioperez.modelo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepositorio extends JpaRepository<Ticket, Integer> {

}
