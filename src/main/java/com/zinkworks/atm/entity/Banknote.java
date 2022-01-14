package com.zinkworks.atm.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "banknote")
public class Banknote {

	@NotNull
	@Id
    @Column(name = "note_id", nullable = false)
	private int noteId;

	@Column
	private int five;
	@Column
    private int ten;
	@Column
    private int twenty;
	@Column
    private int fifty;

	public Banknote(){

	}

	public Banknote(int noteId, int five, int ten, int twenty, int fifty) {
		this.noteId = noteId;
		this.five = five;
		this.ten = ten;
		this.twenty = twenty;
		this.fifty = fifty;
	}

	public long getTotal() {
		long totalAmount = 0;
		totalAmount = totalAmount + 5 * five + 10 * ten + 20 * twenty + 50 * fifty;
		return totalAmount;
	}

	

}
