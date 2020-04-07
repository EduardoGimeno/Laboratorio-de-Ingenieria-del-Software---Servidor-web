

/**
 * Esta clase reprensenta la entidad Espacio
 *
 * @author Gonzalo Berné
 * @author Eduardo Gimeno
 * @version 2.0, 01/04/2020
 */

package com.LS.Aplicacion.Entidad;

import com.LS.Aplicacion.ObjetoValor.Ubicacion;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Espacio {

    @Id
    private UUID id;

    @NotNull
    private String tipo;

    @NotNull
    @Min(0)
    private int capacidad;

    /*@NotNull
    @Embedded
    @OneToMany
    private List<Equipamiento> equipamiento;
    */

    @NotNull
    private Ubicacion ubicacion;

    public Espacio(String id, String tipo, int capacidad, // List<Equipamiento> equipamiento,
                   Ubicacion ubicacion) {
        this.tipo = tipo;
        this.capacidad = capacidad;
        // this.equipamiento = equipamiento;
        this.ubicacion = ubicacion;
        this.id = UUID.fromString(id);
    }

    public UUID getId() {
        return this.id;
    }

    public String getTipo() {
        return this.tipo;
    }

    public int getCapacidad() {
        return this.capacidad;
    }

    /*public List<Equipamiento> getEquipamiento() {
        return this.equipamiento;
    }
    */

    public Ubicacion getUbicacion() {
        return this.ubicacion;
    }
}