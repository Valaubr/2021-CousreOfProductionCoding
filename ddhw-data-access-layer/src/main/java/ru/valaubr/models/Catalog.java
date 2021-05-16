package ru.valaubr.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Catalog")
public class Catalog extends DataStorage{
}
