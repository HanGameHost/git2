package com.spring.test.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String chip, drip, chipset;
    private Integer ramMb, weed;

    public Computer( String chip, String drip, String chipset, Integer ramMb, Integer weed) {
        this.chip = chip;
        this.drip = drip;
        this.chipset = chipset;
        this.ramMb = ramMb;
        this.weed = weed;
    }

    public Computer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getDrip() {
        return drip;
    }

    public void setDrip(String drip) {
        this.drip = drip;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public Integer getRamMb() {
        return ramMb;
    }

    public void setRamMb(Integer ramMb) {
        this.ramMb = ramMb;
    }

    public Integer getWeed() {
        return weed;
    }

    public void setWeed(Integer weed) {
        this.weed = weed;
    }
}
