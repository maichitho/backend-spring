package com.vietis.carpark.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the park_service database table.
 * 
 */
@Entity
@Table(name = "park_service")
@NamedQuery(name = "ParkService.findAll", query = "SELECT p FROM ParkService p")
public class ParkService implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private ParkServicePK parkServicePk;

}