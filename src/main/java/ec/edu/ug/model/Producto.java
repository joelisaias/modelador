package ec.edu.ug.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
public class Producto implements Serializable,AbstractEntity {
	private static final long serialVersionUID = 1L;


	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Getter @Setter
	private String codigo;

	@Getter @Setter
	private String descripcion;

	@Getter @Setter
	private BigDecimal precio;

	@Getter @Setter
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechacreacion;

	@Getter @Setter
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechamodificacion;

	@Getter @Setter
	private String estado;


	@Getter @Setter
	@Formula("(case estado when 'A' then 'Activo' else 'Inactivo' end)")
	private String estadoDesc;
	public Producto() {
	}
	
	@Override
	public String toString() {
		return "("+getId()+") ["+getCodigo()+"] - "+getDescripcion();
	}

}