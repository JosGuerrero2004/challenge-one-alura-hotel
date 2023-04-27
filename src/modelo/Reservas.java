package modelo;

import java.math.BigDecimal;
import java.sql.Date;

public class Reservas {
	private Integer id;
	private Date checkIn, checkOut;
	private BigDecimal valor;
	private String formaPago;
	
	public Reservas(Date checkIn, Date checkOut, BigDecimal valor, String formaPago) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.valor = valor;
		this.formaPago = formaPago;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public String getFormaPago() {
		return formaPago;
	}
	
	@Override
	public String toString() {
		return String.format("{id: %s, valor: %s, formaPago: %s", 
				this.id,
				this.valor,
				this.formaPago);
	}
}
