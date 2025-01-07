package com.mycompany.cabotalejandro_pruebatech2.logica;

import com.mycompany.cabotalejandro_pruebatech2.logica.Ciudadano;
import com.mycompany.cabotalejandro_pruebatech2.logica.EstadoTurno;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-01-07T19:36:41")
@StaticMetamodel(Turno.class)
public class Turno_ { 

    public static volatile SingularAttribute<Turno, String> descripcion;
    public static volatile SingularAttribute<Turno, LocalDate> fecha;
    public static volatile SingularAttribute<Turno, EstadoTurno> estado;
    public static volatile SingularAttribute<Turno, Integer> numero;
    public static volatile SingularAttribute<Turno, Long> id;
    public static volatile SingularAttribute<Turno, Ciudadano> ciudadano;

}